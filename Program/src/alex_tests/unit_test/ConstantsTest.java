package alex_tests.unit_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utilities.Constants;

/**
 * This class tests the Constants class.
 * @author Alexander Fleming
 */
public class ConstantsTest {

    @Test
    public void testIsMacOS() {
        // This test will depend on the system the test is run on
        String os = System.getProperty("os.name");
        assertEquals(os.contains("Mac"), Constants.isMacOS());
    }

    @Test
    public void testPlayerTokenImages() {
        assertAll(
            () -> assertNotNull(Constants.PlayerTokenImages.RED_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.YELLOW_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.GREEN_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.BLUE_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.PURPLE_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.ORANGE_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.CYAN_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.MAGENTA_PAWN),
            () -> assertNotNull(Constants.PlayerTokenImages.RED_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.YELLOW_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.GREEN_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.BLUE_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.PURPLE_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.ORANGE_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.CYAN_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.MAGENTA_KNIGHT),
            () -> assertNotNull(Constants.PlayerTokenImages.RED_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.YELLOW_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.GREEN_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.BLUE_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.PURPLE_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.ORANGE_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.CYAN_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.MAGENTA_ROOK),
            () -> assertNotNull(Constants.PlayerTokenImages.RED_KING),
            () -> assertNotNull(Constants.PlayerTokenImages.YELLOW_KING),
            () -> assertNotNull(Constants.PlayerTokenImages.GREEN_KING),
            () -> assertNotNull(Constants.PlayerTokenImages.BLUE_KING),
            () -> assertNotNull(Constants.PlayerTokenImages.PURPLE_KING),
            () -> assertNotNull(Constants.PlayerTokenImages.ORANGE_KING),
            () -> assertNotNull(Constants.PlayerTokenImages.CYAN_KING),
            () -> assertNotNull(Constants.PlayerTokenImages.MAGENTA_KING)
        );
    }

    @Test
    public void testTileImages() {
        assertAll(
            () -> assertNotNull(Constants.TileImages.FORTUNE_TILE),
            () -> assertNotNull(Constants.TileImages.GO_TILE),
            () -> assertNotNull(Constants.TileImages.GOTOJAIL_TILE),
            () -> assertNotNull(Constants.TileImages.JAIL_TILE),
            () -> assertNotNull(Constants.TileImages.TAX_TILE),
            () -> assertNotNull(Constants.TileImages.WORK_TILE),
            () -> assertNotNull(Constants.TileImages.BANK_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_WOOD_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_STONE_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_FISHSTALL_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_VEGGIESTALL_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_BAKERSTALL_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_TANNER_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_MILL_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_SMITH_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_BATH_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_BAKERY_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_BUTCHER_TILE),
            () -> assertNotNull(Constants.TileImages.SUNDAY_CHURCH_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_WAREHOUSE_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_ALCHEMY_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_STABLE_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_COBBLER_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_GENERALSTORE_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_SILVERSMITH_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_ARMORER_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_TAILOR_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_WEAPONSMITH_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_CHURCH_TILE),
            () -> assertNotNull(Constants.TileImages.PROPERTY_CASTLE_TILE),
            () -> assertNotNull(Constants.TileImages.TAVERN_TILE)
        );
    }

    @Test
    public void testDiceImages() {
        assertAll(
            () -> assertNotNull(Constants.DiceImages.WHITE_1),
            () -> assertNotNull(Constants.DiceImages.WHITE_2),
            () -> assertNotNull(Constants.DiceImages.WHITE_3),
            () -> assertNotNull(Constants.DiceImages.WHITE_4),
            () -> assertNotNull(Constants.DiceImages.WHITE_5),
            () -> assertNotNull(Constants.DiceImages.WHITE_6),
            () -> assertNotNull(Constants.DiceImages.RED_1),
            () -> assertNotNull(Constants.DiceImages.RED_2),
            () -> assertNotNull(Constants.DiceImages.RED_3),
            () -> assertNotNull(Constants.DiceImages.RED_4),
            () -> assertNotNull(Constants.DiceImages.RED_5),
            () -> assertNotNull(Constants.DiceImages.RED_6)
        );
    }

    @Test
    public void testBoardImages() {
        assertNotNull(Constants.BoardImages.BOARD_IMAGE);
    }

    @Test
    public void testAudioFiles() {
        assertAll(
            () -> assertTrue(Constants.AudioFiles.bgMusic.exists()),
            () -> assertTrue(Constants.AudioFiles.duraw.exists()),
            () -> assertTrue(Constants.AudioFiles.victory.exists()),
            () -> assertTrue(Constants.AudioFiles.hips.exists()),
            () -> assertTrue(Constants.AudioFiles.yeah.exists())
        );
    }

    @Test
    public void testPlayerTokenImagesYellowPawn() {
        assertNotNull(Constants.PlayerTokenImages.YELLOW_PAWN);
    }

    //TODO: Add similar tests for other images in PlayerTokenImages

    @Test
    public void testDiceImagesWhite2() {
        assertNotNull(Constants.DiceImages.WHITE_2);
    }


    @Test
    public void testBoardImagesPaperBackgroundImage() {
        assertNotNull(Constants.BoardImages.getPaperBackgroundImage());
    }

    @Test
    public void testAudioFilesDuraw() {
        assertTrue(Constants.AudioFiles.duraw.exists());
    }
}
