package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Entities.StaticEntities.BreakBlock;
import Game.World.Map;
import Game.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage[] backgrounds;
    public static BufferedImage[] backgrounds2;

    public static BufferedImage[] marioSmallWalkLeft;
    public static BufferedImage[] marioSmallWalkRight;
    public static BufferedImage[] marioSmallJumpLeft;//also store ide sprites like looking up or down
    public static BufferedImage[] marioSmallJumpRight;

    public static BufferedImage[] marioBigWalkLeft;
    public static BufferedImage[] marioBigWalkRight;
    public static BufferedImage[] marioBigRunLeft;
    public static BufferedImage[] marioBigRunRight;
    public static BufferedImage[] marioBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
    public static BufferedImage[] marioBigJumpRight;
    
    public static BufferedImage[] DKIdleRight;
    public static BufferedImage[] DKIdleLeft;
    public static BufferedImage[] DKWalkRight;
    public static BufferedImage[] DKWalkLeft;
    public static BufferedImage[] DKJumpRight;
    public static BufferedImage[] DKJumpLeft;
    public static BufferedImage[] DKLook;
    public static BufferedImage[] DKTurn;
    
    public static BufferedImage[] FKIdleRight;
    public static BufferedImage[] FKIdleLeft;
    public static BufferedImage[] FKWalkRight;
    public static BufferedImage[] FKWalkLeft;
    public static BufferedImage[] FKJumpRight;
    public static BufferedImage[] FKJumpLeft;
    public static BufferedImage[] FKLook;
    public static BufferedImage[] FKTurn;
    
    
    
    
    public static BufferedImage[] item;
    public static BufferedImage[] enemy;
    public static BufferedImage[] hitWall;
    public static BufferedImage[] enemyFG;
    public static BufferedImage[] enemyGB1;
    public static BufferedImage[] enemyGB2;
    public static BufferedImage[] enemyGB3;
    public static BufferedImage enemyBL;
    public static BufferedImage enemyPS;
    public static BufferedImage enemyHT;
    public static BufferedImage enemySmash;


    public static BufferedImage[] goomba;


    public static BufferedImage title;
    public static BufferedImage Pause;
    public static BufferedImage Cursor;

    public static BufferedImage testMap;
    public static BufferedImage testMaptwo;

    public static BufferedImage breakBlock;
    public static BufferedImage misteryBlock;
    public static BufferedImage surfaceBlock;
    public static BufferedImage boundBlock;
    public static BufferedImage mushroom;
    public static BufferedImage goombaDies;

    private SpriteSheet mainmenuSpriteSheet;
    private SpriteSheet backgroundSpriteSheet;
    private SpriteSheet interactableSpriteSheet;
    private SpriteSheet playerSpriteSheet;
    private SpriteSheet blockSpriteSheet;
    private SpriteSheet goombaSpriteSheet;
    private SpriteSheet SSpriteSheet;
    private SpriteSheet SAttackSpriteSheet;
    private SpriteSheet DonkeyKongSheet;
    private SpriteSheet FunkyKongSheet;

    public Images() {

        butstart = new BufferedImage[3];

        backgrounds = new BufferedImage[9];
        backgrounds2 = new BufferedImage[6];

        marioSmallWalkLeft = new BufferedImage[2];
        marioSmallWalkRight = new BufferedImage[2];
        marioSmallJumpLeft = new BufferedImage[4];
        marioSmallJumpRight = new BufferedImage[4];
        item = new BufferedImage[19];
        enemy = new BufferedImage[9];
        hitWall = new BufferedImage[44];
        enemyFG = new BufferedImage[9];
        enemyGB1 = new BufferedImage[3];
        enemyGB2 = new BufferedImage[2];
        enemyGB3 = new BufferedImage[3];


        marioBigWalkLeft = new BufferedImage[2];
        marioBigWalkRight = new BufferedImage[2];
        marioBigRunLeft = new BufferedImage[2];
        marioBigRunRight = new BufferedImage[2];
        marioBigJumpLeft = new BufferedImage[5];
        marioBigJumpRight = new BufferedImage[5];
        goomba = new BufferedImage[2];

        DKIdleRight = new BufferedImage[5];
        DKIdleLeft = new BufferedImage[5];
        DKWalkRight = new BufferedImage[6];
        DKWalkLeft = new BufferedImage[6];
        DKJumpRight = new BufferedImage[8];
        DKJumpLeft = new BufferedImage[8];
        DKLook = new BufferedImage[4];
        DKTurn = new BufferedImage[2];
        
        
        FKIdleRight = new BufferedImage[4];
        FKIdleLeft = new BufferedImage[4];
        FKWalkRight = new BufferedImage[8];
        FKWalkLeft = new BufferedImage[8];
        FKJumpRight = new BufferedImage[8];
        FKJumpLeft = new BufferedImage[8];
        FKLook = new BufferedImage[4];
        FKTurn = new BufferedImage[2];
        

        try {


            //spriteSheets
            mainmenuSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/NewmainmenuSheet.png")));
            backgroundSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/backgroundSheet.png")));
            interactableSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/interactablesSheet.png")));
            playerSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/marioSNESSheet.png")));
            blockSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/blocksSheet.png")));
            goombaSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/goombaSprite.png")));
            SSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/Sheets.png")));
            SAttackSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/enemySheet2.png")));
            DonkeyKongSheet =  new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/donkey-kong-2.png")));
            FunkyKongSheet =  new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/FunkyKongSheet.png")));

            //Images
            title = mainmenuSpriteSheet.crop(16,16,256,224);
            Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/Pause.png"));
            Cursor = ImageIO.read(getClass().getResourceAsStream("/Sheets/cursor.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut

            backgrounds[0] = backgroundSpriteSheet.crop(2,2,512,432);
            backgrounds[1] = backgroundSpriteSheet.crop(516,2,512,432);
            backgrounds[2] = backgroundSpriteSheet.crop(2,438,512,432);
            backgrounds[3] = backgroundSpriteSheet.crop(516,438,512,432);
            backgrounds[4] = backgroundSpriteSheet.crop(2,872,512,432);
            backgrounds[5] = backgroundSpriteSheet.crop(516,872,512,432);
            backgrounds[6] = backgroundSpriteSheet.crop(2,1306,512,432);
            backgrounds[7] = backgroundSpriteSheet.crop(516,1306,512,432);
            backgrounds[8] = backgroundSpriteSheet.crop(2,1740,512,432);
            
            backgrounds2[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/GrasslandBackground.png"));
            backgrounds2[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DarkBackground.png"));
            backgrounds2[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/CanyonBackground.png"));
            backgrounds2[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DesertBackground.png"));
            backgrounds2[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow1Background.png"));
            backgrounds2[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow2Background.png"));


            //player sprites
            //Small
            marioSmallWalkLeft[0] = playerSpriteSheet.crop(169,0,14,20);
            marioSmallWalkLeft[1] = playerSpriteSheet.crop(49,0,15,19);

            marioSmallWalkRight[0] = playerSpriteSheet.crop(209,0,14,20);
            marioSmallWalkRight[1] = playerSpriteSheet.crop(328,0,15,19);

            marioSmallJumpLeft[0] = playerSpriteSheet.crop(168,39,16,22);
            marioSmallJumpLeft[1] = playerSpriteSheet.crop(128,40,16,20);
            marioSmallJumpLeft[2] = playerSpriteSheet.crop(49,39,15,21);//up
            marioSmallJumpLeft[3] = playerSpriteSheet.crop(89,43,15,14);//down

            marioSmallJumpRight[0] = playerSpriteSheet.crop(208,39,16,22);
            marioSmallJumpRight[1] = playerSpriteSheet.crop(248,40,16,20);
            marioSmallJumpRight[2] = playerSpriteSheet.crop(328,39,15,21);//up
            marioSmallJumpRight[3] = playerSpriteSheet.crop(288,43,15,14);//down

            // Big
            marioBigWalkLeft[0] = playerSpriteSheet.crop(169,76,15,28);
            marioBigWalkLeft[1] = playerSpriteSheet.crop(8,76,16,28);

            marioBigWalkRight[0] = playerSpriteSheet.crop(208,76,15,28);
            marioBigWalkRight[1] = playerSpriteSheet.crop(368,76,16,28);

            marioBigRunLeft[0] = playerSpriteSheet.crop(169,76,15,28);
            marioBigRunLeft[1] = playerSpriteSheet.crop(48,76,16,27);

            marioBigRunRight[0] = playerSpriteSheet.crop(208,76,15,28);
            marioBigRunRight[1] = playerSpriteSheet.crop(328,76,16,27);

            marioBigJumpLeft[0] = playerSpriteSheet.crop(168,114,16,31);
            marioBigJumpLeft[1] = playerSpriteSheet.crop(128,115,16,29);
            marioBigJumpLeft[2] = playerSpriteSheet.crop(129,196,15,27);//up
            marioBigJumpLeft[3] = playerSpriteSheet.crop(88,122,16,15);//down
            marioBigJumpLeft[4] = playerSpriteSheet.crop(8,115,16,29);//change

            marioBigJumpRight[0] = playerSpriteSheet.crop(208,114,16,31);
            marioBigJumpRight[1] = playerSpriteSheet.crop(248,115,16,29);
            marioBigJumpRight[2] = playerSpriteSheet.crop(248,196,15,27);//up
            marioBigJumpRight[3] = playerSpriteSheet.crop(287,122,16,15);//down
            marioBigJumpRight[4] = playerSpriteSheet.crop(368,115,16,29);//Change
            
            
            
            DKIdleRight[0] = DonkeyKongSheet.crop(215, 154, 29, 30);
            DKIdleRight[1] = DonkeyKongSheet.crop(243, 154, 29, 30);
            DKIdleRight[2] = DonkeyKongSheet.crop(273, 154, 29, 30);
            DKIdleRight[3] = DonkeyKongSheet.crop(304, 154, 29, 30);
            DKIdleRight[4] = DonkeyKongSheet.crop(332, 154, 29, 30);
            
            DKIdleLeft[0] = DonkeyKongSheet.crop(185, 154, 29, 30);
            DKIdleLeft[1] = DonkeyKongSheet.crop(157, 154, 29, 30);
            DKIdleLeft[2] = DonkeyKongSheet.crop(127, 154, 29, 30);
            DKIdleLeft[3] = DonkeyKongSheet.crop(96, 154, 29, 30);
            DKIdleLeft[4] = DonkeyKongSheet.crop(68, 154, 29, 30);
            
            DKWalkRight[0] = DonkeyKongSheet.crop(244, 256, 29, 30);
            DKWalkRight[1] = DonkeyKongSheet.crop(273, 256, 29, 30);
            DKWalkRight[2] = DonkeyKongSheet.crop(302, 256, 29, 30);
            DKWalkRight[3] = DonkeyKongSheet.crop(331, 256, 29, 30);
            DKWalkRight[4] = DonkeyKongSheet.crop(360, 256, 29, 30);
            DKWalkRight[5] = DonkeyKongSheet.crop(389, 256, 29, 30);
            
            DKWalkLeft[0] = DonkeyKongSheet.crop(156, 256, 29, 30);
            DKWalkLeft[1] = DonkeyKongSheet.crop(128, 256, 29, 30);
            DKWalkLeft[2] = DonkeyKongSheet.crop(98, 256, 29, 30);
            DKWalkLeft[3] = DonkeyKongSheet.crop(69, 256, 29, 30);
            DKWalkLeft[4] = DonkeyKongSheet.crop(40, 256, 29, 30);
            DKWalkLeft[5] = DonkeyKongSheet.crop(11, 256, 29, 30);
            
            DKJumpRight[0] = DonkeyKongSheet.crop(213, 481, 28, 30);
            DKJumpRight[1] = DonkeyKongSheet.crop(240, 481, 28, 30);
            DKJumpRight[2] = DonkeyKongSheet.crop(267, 481, 28, 30);
            DKJumpRight[3] = DonkeyKongSheet.crop(294, 481, 27, 30);
            DKJumpRight[4] = DonkeyKongSheet.crop(343, 481, 24, 30);
            DKJumpRight[5] = DonkeyKongSheet.crop(294, 481, 27, 30);
            DKJumpRight[6] = DonkeyKongSheet.crop(371, 481, 28, 30);
            DKJumpRight[7] = DonkeyKongSheet.crop(399, 481, 28, 30);
            
            DKJumpLeft[0] = DonkeyKongSheet.crop(188, 481, 28, 30);
            DKJumpLeft[1] = DonkeyKongSheet.crop(161, 481, 28, 30);
            DKJumpLeft[2] = DonkeyKongSheet.crop(134, 481, 28, 30);
            DKJumpLeft[3] = DonkeyKongSheet.crop(108, 481, 27, 30);
            DKJumpLeft[4] = DonkeyKongSheet.crop(85, 481, 24, 30);
            DKJumpLeft[5] = DonkeyKongSheet.crop(59, 481, 27, 30);
            DKJumpLeft[6] = DonkeyKongSheet.crop(30, 481, 28, 30);
            DKJumpLeft[7] = DonkeyKongSheet.crop(2, 481, 28, 30);
            
            DKLook[0] = DonkeyKongSheet.crop(243, 413, 29, 30);
            DKLook[1] = DonkeyKongSheet.crop(157, 413, 29, 30);
            DKLook[2] = DonkeyKongSheet.crop(215, 576, 29, 28);
            DKLook[3] = DonkeyKongSheet.crop(185, 576, 29, 28);
            
            DKTurn[0] = DonkeyKongSheet.crop(214, 546, 29, 30);
            DKTurn[1] = DonkeyKongSheet.crop(187, 546, 29, 30);
            
            
            
            FKIdleRight[0] = FunkyKongSheet.crop(7, 7, 36, 36);
            FKIdleRight[1] = FunkyKongSheet.crop(49, 7, 36, 36);
            FKIdleRight[2] = FunkyKongSheet.crop(88, 7, 36, 36);
            FKIdleRight[3] = FunkyKongSheet.crop(128, 7, 36, 36);
            
            FKIdleLeft[0] = FunkyKongSheet.crop(124, 300, 36, 36);
            FKIdleLeft[1] = FunkyKongSheet.crop(82, 300, 36, 36);
            FKIdleLeft[2] = FunkyKongSheet.crop(43, 300, 36, 36);
            FKIdleLeft[3] = FunkyKongSheet.crop(3, 300, 36, 36);
            
            FKWalkLeft[0] = FunkyKongSheet.crop(261, 7, 36, 36);
            FKWalkLeft[1] = FunkyKongSheet.crop(299, 7, 36, 36);
            FKWalkLeft[2] = FunkyKongSheet.crop(337, 7, 36, 36);
            FKWalkLeft[3] = FunkyKongSheet.crop(383, 7, 36, 36);
            FKWalkLeft[4] = FunkyKongSheet.crop(422, 7, 36, 36);
            FKWalkLeft[5] = FunkyKongSheet.crop(468, 7, 36, 36);
            FKWalkLeft[6] = FunkyKongSheet.crop(513, 7, 36, 36);
            FKWalkLeft[7] = FunkyKongSheet.crop(551, 7, 36, 36);
            
            FKWalkRight[0] = FunkyKongSheet.crop(291, 387, 36, 36);
            FKWalkRight[1] = FunkyKongSheet.crop(253, 387, 36, 36);
            FKWalkRight[2] = FunkyKongSheet.crop(215, 387, 36, 36);
            FKWalkRight[3] = FunkyKongSheet.crop(169, 387, 36, 36);
            FKWalkRight[4] = FunkyKongSheet.crop(130, 387, 36, 36);
            FKWalkRight[5] = FunkyKongSheet.crop(84, 387, 36, 36);
            FKWalkRight[6] = FunkyKongSheet.crop(39, 387, 36, 36);
            FKWalkRight[7] = FunkyKongSheet.crop(1, 387, 36, 36);
            
            FKJumpRight[0] = FunkyKongSheet.crop(325, 346, 36, 36);
            FKJumpRight[1] = FunkyKongSheet.crop(283, 346, 36, 36);
            FKJumpRight[2] = FunkyKongSheet.crop(242, 346, 36, 36);
            FKJumpRight[3] = FunkyKongSheet.crop(191, 346, 36, 36);
            FKJumpRight[4] = FunkyKongSheet.crop(151, 346, 36, 36);
            FKJumpRight[5] = FunkyKongSheet.crop(103, 346, 36, 36);
            FKJumpRight[6] = FunkyKongSheet.crop(55, 346, 36, 36);
            FKJumpRight[7] = FunkyKongSheet.crop(0, 346, 36, 36);
            
            
            
            
            item[0] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL1.png"));
            item[1] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL2.png"));
            item[2] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL3.png"));
            item[3] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL4.png"));
            item[4] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL5.png"));
            item[5] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL6.png"));
            item[6] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL7.png"));
            item[7] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL8.png"));
            item[8] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL9.png"));
            item[9] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL10.png"));
            item[10] =  item[9];
            item[11] =  item[8];
            item[12] =  item[7];
            item[13] =  item[6];
            item[14] =  item[5];
            item[15] =  item[4];
            item[16] =  item[3];
            item[17] =  item[2];
            item[18] =  item[1];
          
            hitWall[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO1.png"));
            hitWall[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO2.png"));
            hitWall[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO3.png"));
            hitWall[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO4.png"));
            hitWall[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO5.png"));
            hitWall[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO6.png"));
            hitWall[6] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO7.png"));
            hitWall[7] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO8.png"));
            hitWall[8] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO9.png"));
            hitWall[9] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO10.png"));
            hitWall[10] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO11.png"));
            hitWall[11] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO12.png"));
            hitWall[12] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO13.png"));
            hitWall[13] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO14.png"));
            hitWall[14] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO15.png"));
            hitWall[15] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO17.png"));
            hitWall[16] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO18.png"));
            hitWall[17] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO19.png"));
            hitWall[18] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO20.png"));
            hitWall[19] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO21.png"));
            hitWall[20] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO22.png"));
            hitWall[21] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO23.png"));
            hitWall[22] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO24.png"));
            hitWall[23] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO25.png"));
            hitWall[24] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO26.png"));
            hitWall[25] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO27.png"));
            hitWall[26] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO28.png"));
            hitWall[27] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO29.png"));
            hitWall[28] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO30.png"));
            hitWall[29] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO31.png"));
            hitWall[30] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO32.png"));
            hitWall[31] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO33.png"));
            hitWall[32] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO35.png"));
            hitWall[33] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO36.png"));
            hitWall[34] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO37.png"));
            hitWall[35] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO38.png"));
            hitWall[36] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO39.png"));
            hitWall[37] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO40.png"));
            hitWall[38] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO41.png"));
            hitWall[39] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO42.png"));
            hitWall[40] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO43.png"));
            hitWall[41] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO44.png"));
            hitWall[42] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO45.png"));
            hitWall[43] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO46.png"));

            enemy[0] = SSpriteSheet.crop(8,14,64,45);
            enemy[1] = SSpriteSheet.crop(88,21,60,38);
            enemy[2] = SSpriteSheet.crop(165,25,55,37);
            enemy[3] = SSpriteSheet.crop(242,28,50,35);
            enemy[4] = SSpriteSheet.crop(315,28,48,34);
            enemy[5] = SSpriteSheet.crop(381,27,57,35);
            enemy[6] = SSpriteSheet.crop(454,25,61,35);
            enemy[7] = SSpriteSheet.crop(525,19,62,37);
            enemy[8] = SSpriteSheet.crop(75,89,51,41);

            enemyFG[0] = SAttackSpriteSheet.crop(3,115,66,45);
            enemyFG[1] = SAttackSpriteSheet.crop(72,103,71,55);
            enemyFG[2] =  SAttackSpriteSheet.crop(149,101,64,62);
            enemyFG[3] =  SAttackSpriteSheet.crop(215,102,80,62);
            enemyFG[4] =  SAttackSpriteSheet.crop(5,167,79,62);
            enemyFG[5] =  SAttackSpriteSheet.crop(90,167,93,62);
            enemyFG[6] = SAttackSpriteSheet.crop(188,167,99,72);
            enemyFG[7] =  enemyFG[1];
            enemyFG[8] =  enemyFG[0];

            enemyGB1[0] = SAttackSpriteSheet.crop(3,248,53,48);
            enemyGB1[1] = SAttackSpriteSheet.crop(68,247,61,46);
            enemyGB1[2] =  SAttackSpriteSheet.crop(147,252,48,40);


            enemyGB2[0] = SAttackSpriteSheet.crop(205,250,34,41);
            enemyGB2[1] = SAttackSpriteSheet.crop(252,251,45,44);


            enemyGB3[0] = enemyGB1[2];
            enemyGB3[1] = enemyGB1[1];
            enemyGB3[2] =  enemyGB1[0];

            enemyPS= SAttackSpriteSheet.crop(68,358,29,64);
            enemyHT= SAttackSpriteSheet.crop(178,357,52,62);
            enemySmash= SAttackSpriteSheet.crop(9,302,51,41);
            enemyBL= SAttackSpriteSheet.crop(188,184,41,55);

            //maps
            testMap = ImageIO.read(getClass().getResourceAsStream("/maps/testmap1.png"));
            testMaptwo = ImageIO.read(getClass().getResourceAsStream("/maps/testmap2.png"));

            //blocks
            boundBlock = interactableSpriteSheet.crop(12,73,16,16);
            misteryBlock = interactableSpriteSheet.crop(32,93,16,16);
            surfaceBlock = interactableSpriteSheet.crop(112,93,16,16);
            //breakBlock = blockSpriteSheet.crop(272,112,16,16);
            breakBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/brick.png"));

            //items
            mushroom = interactableSpriteSheet.crop(112,34,16,16);

            //enemy
            goomba[0]=goombaSpriteSheet.crop(119,40,162,162);
            goomba[1]= goombaSpriteSheet.crop(329,40,162,162);
            goombaDies=goombaSpriteSheet.crop(539,100,162,81);


        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }   
    public static  void makeMap(int i, int j, int k, int z,  Map map, Handler h) {
    	for(int x = i; x < k; x++) {
    		map.addBlock(new BreakBlock( x * j, z * j, j, j, h));
    	}	
    }

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image ( who made that so complicated :< )
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }



}
