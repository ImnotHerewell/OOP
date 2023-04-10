package ru.nsu.valikov.petukhon.handlers;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import java.util.Deque;
import ru.nsu.valikov.petukhon.PetukhonType;
import ru.nsu.valikov.petukhon.components.SnakeHeadComponent;
import ru.nsu.valikov.petukhon.components.SnakeTailComponent;
import ru.nsu.valikov.petukhon.components.SnakeType;
import ru.nsu.valikov.petukhon.factories.FoodFactory;

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
        return new CollisionHandler(PetukhonType.SNAKE_HEAD, PetukhonType.SNAKE_TAIL) {
            @Override
            protected void onCollisionBegin(Entity snakeHead, Entity snakeTail) {
                var tail = snakeHead.getComponent(SnakeHeadComponent.class);
                if (tail.getTailParts().contains(snakeTail) && tail.getTailParts().size() > 2) {
                    System.exit(0);
                }
                final SnakeHeadComponent headOfAnotherSnake = snakeTail.getComponent(
                    SnakeTailComponent.class).getParent();
                final Deque<Entity> tailParts = headOfAnotherSnake.getTailParts();
                Entity lastPart; // ломается
                while ((lastPart = tailParts.getLast()) != snakeTail) {
                    lastPart.removeFromWorld();
                    tailParts.pop();
                    tail.eat();
                }
                lastPart.removeFromWorld();
                tail.eat();
                tailParts.pop();
            }
        };
    }

    /**
     * Handle situation when snake's head clashes with decoration, and it ends player's game or
     * others snake's die.
     *
     * @return Collision Handler
     */
    public static CollisionHandler clashBetweenHeadAndDecoration() {
        return new CollisionHandler(PetukhonType.SNAKE_HEAD, PetukhonType.DECORATION) {
            @Override
            protected void onCollisionBegin(Entity snakeHead, Entity decoration) {
                snakeHead.removeFromWorld();
                if (snakeHead.getComponent(SnakeHeadComponent.class).getType()
                    .equals(SnakeType.PLAYER)) {
                    System.exit(0);
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
        return new CollisionHandler(PetukhonType.SNAKE_HEAD, PetukhonType.FOOD) {
            @Override
            protected void onCollisionEnd(Entity snake, Entity food) {
                food.removeFromWorld();
                getGameWorld().spawn(FoodFactory.NAME);
                snake.getComponent(SnakeHeadComponent.class).eat();
            }
        };
    }
}
