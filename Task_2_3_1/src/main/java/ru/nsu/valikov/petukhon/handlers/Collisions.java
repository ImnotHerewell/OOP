package ru.nsu.valikov.petukhon.handlers;

import static com.almasb.fxgl.core.math.FXGLMath.abs;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import java.util.Deque;
import ru.nsu.valikov.petukhon.components.AiComponent;
import ru.nsu.valikov.petukhon.components.AiType;
import ru.nsu.valikov.petukhon.components.SnakeHeadComponent;
import ru.nsu.valikov.petukhon.components.SnakeTailComponent;
import ru.nsu.valikov.petukhon.components.SnakeType;
import ru.nsu.valikov.petukhon.factories.FactoryType;
import ru.nsu.valikov.petukhon.factories.FoodFactory;
import ru.nsu.valikov.petukhon.view.Artist;

/**
 * Contain collision handler methods.
 */
public class Collisions {

    /**
     * Handle situation when snakes clash its tail or tail of another snake.
     *
     * @return Collision Handler to handle bad clashes.
     */
    public static CollisionHandler clashBetweenHeadAndTail() {
        return new CollisionHandler(FactoryType.SNAKE_HEAD, FactoryType.SNAKE_TAIL) {
            @Override
            protected void onCollisionBegin(Entity snakeHead, Entity snakeTail) {
                var tail = snakeHead.getComponent(SnakeHeadComponent.class);
                if (removeIfYourself(tail, snakeTail, snakeHead)) {
                    return;
                }
                removeCollisionParts(snakeTail, tail);
            }
        };
    }

    private static boolean removeIfYourself(SnakeHeadComponent tail, Entity snakeTail,
                                            Entity snakeHead) {
        if (tail.getTailParts().contains(snakeTail) && tail.getTailParts().size() > 2) {
            if (tail.getType() == SnakeType.PLAYER) {
                Artist.gameOver(false);
            }
            tail.getTailParts().forEach(Entity::removeFromWorld);
            snakeHead.removeFromWorld();
            return true;
        }
        return false;
    }

    private static void removeCollisionParts(Entity snakeTail, SnakeHeadComponent tail) {
        final SnakeHeadComponent headOfAnotherSnake = snakeTail.getComponent(
                SnakeTailComponent.class).getParent();
        final Deque<Entity> tailParts = headOfAnotherSnake.getTailParts();
        Entity lastPart;
        while (!(lastPart = tailParts.getLast()).getPosition().equals(snakeTail.getPosition())) {
            lastPart.removeFromWorld();
            tailParts.removeLast();
            tail.eat();
        }
        lastPart.removeFromWorld();
        tail.eat();
        tailParts.removeLast();
    }

    /**
     * Handle situation when snake's head clashes with decoration, and it ends player's game or
     * others snake's die.
     *
     * @return Collision Handler
     */
    public static CollisionHandler clashBetweenHeadAndDecoration() {
        return new CollisionHandler(FactoryType.SNAKE_HEAD, FactoryType.DECORATION) {
            @Override
            protected void onCollisionBegin(Entity snakeHead, Entity decoration) {
                snakeHead.removeFromWorld();
                if (snakeHead.getComponent(SnakeHeadComponent.class).getType().equals(
                        SnakeType.PLAYER)) {
                    Artist.gameOver(false);
                }
            }
        };
    }

    /**
     * Handle situation when snake's head clashes with the food, food respawns, snake's body length
     * increases by 1.
     *
     * @return Collision handler
     */
    public static CollisionHandler clashBetweenHeadAndFood() {
        return new CollisionHandler(FactoryType.SNAKE_HEAD, FactoryType.FOOD) {
            @Override
            protected synchronized void onCollisionBegin(Entity snake, Entity food) {
                food.removeFromWorld();
                getGameWorld().spawn(FoodFactory.NAME);
                double minimalRange = Integer.MAX_VALUE;
                var newFood = food;
                for (var entity : getGameWorld().getEntities()) {
                    if (entity.isType(FactoryType.FOOD)) {
                        double distance = abs(entity.getPosition().distance(snake.getPosition()));
                        if (distance < minimalRange) {
                            newFood = entity;
                            minimalRange = distance;
                        }
                    }
                }
                AiComponent component;
                for (var entity : getGameWorld().getEntitiesByComponent(AiComponent.class)) {
                    if (entity.hasComponent(AiComponent.class) && (component = entity.getComponent(
                            AiComponent.class)).getAiType() == AiType.EAT) {
                        component.setGoal(newFood);
                    }
                }
                snake.getComponent(SnakeHeadComponent.class).eat();
            }
        };
    }

    /**
     * Handle situation when two head clashes with each other. AI always wins the player.
     *
     * @return Collision handler
     */
    public static CollisionHandler clashBetweenHeadAndHead() {
        return new CollisionHandler(FactoryType.SNAKE_HEAD, FactoryType.SNAKE_HEAD) {
            @Override
            protected void onCollisionBegin(Entity snakeHeadFirst, Entity snakeHeadSecond) {
                var head = snakeHeadSecond.getComponent(SnakeHeadComponent.class);
                SnakeHeadComponent headOfAnotherSnake = snakeHeadFirst.getComponent(
                        SnakeHeadComponent.class);
                if (snakeHeadFirst.hasComponent(AiComponent.class)) {
                    head = snakeHeadFirst.getComponent(SnakeHeadComponent.class);
                    headOfAnotherSnake = snakeHeadSecond.getComponent(SnakeHeadComponent.class);
                }
                final Deque<Entity> tailParts = headOfAnotherSnake.getTailParts();
                Entity lastPart;
                while (tailParts.size() > 0) {
                    lastPart = tailParts.getLast();
                    lastPart.removeFromWorld();
                    tailParts.removeLast();
                    head.eat();
                }
                if (!headOfAnotherSnake.getEntity().hasComponent(AiComponent.class)) {
                    Artist.gameOver(false);
                }
                headOfAnotherSnake.getEntity().removeFromWorld();
            }
        };
    }
}
