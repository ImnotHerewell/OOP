package ru.nsu.valikov.petukhon;

import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.DEFAULT_WIDTH;
import static ru.nsu.valikov.petukhon.GameUtils.FIELD_HEIGHT;
import static ru.nsu.valikov.petukhon.GameUtils.FIELD_WIDTH;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import ru.nsu.valikov.petukhon.factories.DecorationFactory;

/**
 * Parses level *.txt file.
 */
public class LevelLoader {

    /**
     * Parses file and returns list of entities, which program should spawn.
     *
     * @param filename filename
     * @return list of entities
     */
    public List<Entity> takeEntities(String filename) {
        var file = getClass().getClassLoader().getResourceAsStream("levels/" + filename + ".txt");
        if (file == null) {
            throw new NoSuchElementException();
        }
        final List<Entity> entities = new ArrayList<>();
        var factory = new DecorationFactory();
        Scanner scanner = new Scanner(file);
        for (int row = 0; row < FIELD_WIDTH / DEFAULT_WIDTH; row++) {
            var string = scanner.next();
            for (int column = 0; column < FIELD_HEIGHT / DEFAULT_HEIGHT; column++) {
                if (string.charAt(column) == 'd') {
                    var data = new SpawnData();
                    data.put("x", (double) row * DEFAULT_WIDTH);
                    data.put("y", (double) column * DEFAULT_HEIGHT);
                    entities.add(factory.newDecoration(data));
                }
            }
        }
        return entities;
    }
}
