package com.arturoo404.game.generate;

import com.arturoo404.game.entity.*;
import com.arturoo404.game.entity.wolf.Wolf;
import com.arturoo404.game.file.EntityModel;
import com.arturoo404.game.file.EntityPosition;
import com.arturoo404.game.generate.difficulty.Difficulty;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.arturoo404.game.generate.difficulty.GameDifficulty.*;
import static javafx.scene.shape.StrokeLineCap.ROUND;
import static javafx.scene.shape.StrokeType.OUTSIDE;


public class GenerateEntityModel {

    private final AnchorPane pane;

    public GenerateEntityModel(AnchorPane pane) {
        this.pane = pane;
    }

    public List<Wolf> generateWolfModel(EntityModel entityModel, LivingEntities livingEntities, Difficulty difficulty){
        List<Wolf> wolfList = new ArrayList<>();
        for (EntityPosition model : entityModel.entityPositions()) {
            Wolf wolf = generateWolf(entityModel, model, livingEntities, difficulty);
            wolfList.add(wolf);
        }
        return wolfList;
    }

    private Wolf generateWolf(EntityModel entityModel, EntityPosition entityPosition, LivingEntities livingEntities, Difficulty difficulty){
        Wolf wolf = new Wolf();
        wolf.setAttackSpeed(entityModel.width());
        wolf.setEntityType(entityModel.entityType());
        wolf.setHealth(healthChanger(entityModel.health(),difficulty));
        wolf.setDamage(damageChanger((int) entityModel.damage(),difficulty));
        wolf.setSpeed(entityModel.speed());
        wolf.setRange(entityModel.range());
        wolf.setDetectionRange(entityModel.detectionRange());
        wolf.setDefence(defenceChanger(entityModel.defence(),difficulty));
        wolf.setRectangle(new Rectangle(entityPosition.posX(), entityPosition.posY(), entityModel.width(), entityModel.height()));
        wolf.setLivingEntities(livingEntities);
        wolf.setDetectionStatus(DetectionStatus.IGNORE);
        wolf.setDetectionTimerDefaultValue(entityModel.detectionTimer());
        wolf.setDetectionTimer(entityModel.detectionTimer());
        wolf.setWidth(entityModel.width());
        wolf.setHeight(entityModel.height());
        wolf.setDirection(EntityDirection.LEFT);
        wolf.setEntityMovementAnimation(buildMovementAnimation(wolf, new Image(getClass().getResourceAsStream("/txt/entity/wolf.png"))));
        wolf.getEntityMovementAnimation().init();
        pane.getChildren().add(wolf.getRectangle());

        Circle range = new Circle(entityModel.detectionRange());
        range.setCenterX(entityPosition.posX() + 55);
        range.setCenterY(entityPosition.posY() + 30);
        range.setStrokeWidth(5);
        range.setFill(Color.TRANSPARENT);
        range.setStroke(Color.RED);
        range.setStrokeType(OUTSIDE);
        range.setStrokeLineCap(ROUND);

        pane.getChildren().add(range);

        wolf.setCircle(range);
        return wolf;
    }

    private EntityMovementAnimation buildMovementAnimation(Entity entity, Image image){
        return EntityMovementAnimation.builder()
                .entity(entity)
                .image(image)
                .imageWidth(32)
                .imageHeight(32)
                .imagePositionX(96)
                .imagePositionY(1)
                .pixelReaderMoveX(32)
                .pixelReaderMoveY(32)
                .build();
    }
}
