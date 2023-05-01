package ru.nsu.valikov;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Notebook class for adding and removing notes.
 */
public class RecordBook implements Book {
    private static final Options parserOptions = new Options();
    private static final Map<LocalDateTime, Record> records;
    /*
    Notes are stored here.
     */
    public static File stock = new File("stock.json");

    static {
        if (!stock.exists()) {
            try {
                stock.createNewFile();
                PrintWriter writer = new PrintWriter(stock);
                writer.write("{}");
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        addOpt("add", "Add a record. Usage: record book -add <'title'> <'description'>", 2);
        addOpt("rm", "Delete a record. Usage: record book -rm <'title'>", 1);
        addOpt("show",
                "Show records at datetime segment with matching titles. Usage: record book -show"
                + " <'yyyy-mm-ddThh:mm:ss.sss'> <'yyyy-mm-ddThh:mm:ss.sss'> <'title1'> <'title2'>"
                + " .. <'title i'>\nTitles and datetime segment are optional. ",
                Option.UNLIMITED_VALUES);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        /*
          To read start values from json.
         */
        TypeReference<TreeMap<LocalDateTime, Record>> typeRef = new TypeReference<>() {
        };
        try {
            records = mapper.readValue(stock, typeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addOpt(String nameOpt, String description, int countArgs) {
        Option opt = new Option(nameOpt, description);
        opt.setOptionalArg(true);
        opt.setArgs(countArgs);
        parserOptions.addOption(opt);
    }

    private static void addRecord(CommandLine cmd) {
        String[] arguments = cmd.getOptionValues("add");
        if (arguments.length != 2) {
            showHelp();
            return;
        }
        records.put(LocalDateTime.now(), new Record(arguments[0], arguments[1]));
    }

    private static void removeRecord(CommandLine cmd) {
        String[] arguments = cmd.getOptionValues("rm");
        if (arguments.length != 1) {
            showHelp();
            return;
        }
        String pattern = arguments[0];
        for (var record : records.entrySet()) {
            if (record.getValue().title().equals(pattern)) {
                records.remove(record.getKey());
                break;
            }
        }
    }

    private static void showRecord(CommandLine cmd, int argsLength) throws JsonProcessingException {
        String[] arguments = new String[argsLength - 1];
        if (argsLength != 1) {
            arguments = cmd.getOptionValues("show");
        }
        if (arguments.length == 1) {
            /*
             Illegal using.
             */
            showHelp();
            return;
        }
        if (arguments.length == 0) {
            /*
            Show all records.
             */
            System.out.println(jsonRecordsGetter());
            return;
        }
        /*
        Show records with matching titles.
         */
        List<String> titles =
                new ArrayList<>(Arrays.asList(arguments).subList(2, arguments.length));
        System.out.println(jsonRecordsGetter(LocalDateTime.parse(arguments[0]),
                LocalDateTime.parse(arguments[1]), titles));
    }

    private static void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("notebook", parserOptions);
    }

    /**
     * Return all records in notebook.
     *
     * @return records in json format.
     * @throws JsonProcessingException problem with parsing.
     */
    @JsonAnyGetter
    private static String jsonRecordsGetter() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
    }

    /**
     * Return all records with matching titles.
     *
     * @param from   begin datetime
     * @param to     end datetime
     * @param titles what titles we are looking for
     * @return records with matching titles in json format.
     * @throws JsonProcessingException problem with parsing.
     */
    @JsonAnyGetter
    private static String jsonRecordsGetter(LocalDateTime from, LocalDateTime to,
                                            List<String> titles) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TreeMap<LocalDateTime, Record> newRecords = new TreeMap<>();
        for (var record : records.entrySet()) {
            if (record.getKey().isAfter(from) && record.getKey().isBefore(to)) {
                if (titles.contains(record.getValue().title()) || titles.isEmpty()) {
                    newRecords.put(record.getKey(), record.getValue());
                }
            }
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newRecords);
    }

    /**
     * Entry point.
     *
     * @param args arguments from command line.
     */
    public static void main(String[] args) {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(parserOptions, args);
            if (cmd.hasOption("add") && args.length == 3) {
                addRecord(cmd);
            } else if (cmd.hasOption("rm") && args.length == 2) {
                removeRecord(cmd);
            } else if (cmd.hasOption("show") && args.length != 2) {
                showRecord(cmd, args.length);
            } else {
                showHelp();
            }
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(stock, records);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (ParseException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
