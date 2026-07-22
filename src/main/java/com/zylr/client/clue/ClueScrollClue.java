package com.zylr.client.clue;

import net.minecraft.core.BlockPos;

import java.util.Locale;

public enum ClueScrollClue {
    CLUE_1(1, "", "Between G.E. and Varrock Treasure Chest area", "X:455 Y:63 Z:-541", 455, 63, -541),
    CLUE_2(2, "", "South of Varrock Platebody store / West of Varrock Archery store", "X:612 Y:65 Z:-410", 612, 65, -410),
    CLUE_3(3, "", "South-east of Fred the Farmer's house / Right next to the Lumbridge treasure chest", "X:548 Y:65 Z:84", 548, 65, 84),
    CLUE_4(4, "", "South of Lumbridge East Mine", "X:623 Y:65 Z:399", 623, 65, 399),
    CLUE_5(5, "", "West of Lumbridge Castle / East of encampment at the edge of the Lumbridge Swamp", "X:459 Y:63 Z:217", 459, 63, 217),
    CLUE_6(6, "", "West of the Champion's Guild / West of the Varrock West Mine", "X:430 Y:65 Z:-243", 430, 65, -243),
    CLUE_7(7, "", "South of Port Sarim docks", "X:4 Y:72 Z:355", 4, 72, 355),
    CLUE_8(8, "", "West of Karamja General Store / East of Karamja Volcano", "X:-258 Y:65 Z:395", -258, 65, 395),
    CLUE_9(9, "", "South of Rimmington Mine", "X:-131 Y:65 Z:179", -131, 65, 179),
    CLUE_10(10, "", "West of the Falador farm / North of Port Sarim", "X:4 Y:65 Z:-18", 4, 65, -18),
    CLUE_11(11, "", "North of Sorceress' Garden / South-east of Al Kharid", "X:930 Y:67 Z:390", 930, 67, 390),
    CLUE_12(12, "", "West of the Al Kharid Mine", "X:809 Y:65 Z:-46", 809, 65, -46),
    CLUE_13(13, "", "West of the Lumbridge Cow pen (Eastern) / East of the Lumbridge Chicken coop (Eastern)", "X:660 Y:65 Z:18", 660, 65, 18),
    CLUE_14(14, "", "South of Varrock, inside the fencing West of the Varrock East Mine", "X:676 Y:65 Z:-192", 676, 65, -192),
    CLUE_15(15, "", "West of the G.E.", "X:338 Y:65 Z:-648", 338, 65, -648),
    CLUE_16(16, "", "North-west of the H.A.M. Hideout / South of the two farms", "X:357 Y:65 Z:64", 357, 65, 64),
    CLUE_17(17, "", "The northern tip on the inside of the Lumbridge cow pen (Eastern)", "X:687 Y:63 Z:-35", 687, 63, -35),
    CLUE_18(18, "", "West of Barbarian Village / South-east of the Body Altar (?", "X:134 Y:68 Z:-477", 134, 68, -477),
    CLUE_19(19, "YSITARLIK", "Krystilia", "X:264 Y:63 Z:-695", 264, 63, -695),
    CLUE_20(20, "UESRIQ", "Squire", "X:-132 Y:68 Z:-174", -132, 68, -174),
    CLUE_21(21, "GEGIA", "Aggie", "X:202 Y:62 Z:70", 202, 62, 70),
    CLUE_22(22, "NED", "Ned", "X:237 Y:63 Z:78", 237, 63, 78),
    CLUE_23(23, "LEEAL", "Leela", "X:296 Y:65 Z:65", 296, 65, 65),
    CLUE_24(24, "ELLKAIDY", "Lady Keli", "X:321 Y:63 Z:133", 321, 63, 133),
    CLUE_25(25, "OEJ", "Joe", "X:309 Y:63 Z:133", 309, 63, 133),
    CLUE_26(26, "LAASIS RMREON", "Seaman Lorris", "X:48 Y:66 Z:247", 48, 66, 247),
    CLUE_27(27, "RBUAUY", "Aubury", "X:708 Y:63 Z:-358", 708, 63, -358),
    CLUE_28(28, "OSMCFSEFROTIUC", "Customs Officer", "X:-204 Y:65 Z:415", -204, 65, 415),
    CLUE_29(29, "ETTYH", "Hetty", "X:-162 Y:63 Z:239", -162, 63, 239),
    CLUE_30(30, "YNIRVVISV", "Sir Vyvin", "X:-112 Y:79 Z:-174", -112, 79, -174),
    CLUE_31(31, "GRTUHO", "Thurgo", "X:-87 Y:65 Z:408", -87, 65, 408),
    CLUE_32(32, "IDOCR", "Doric", "X:-207 Y:61 Z:-508", -207, 61, -508),
    CLUE_33(33, "DORLE", "Reldo", "X:569 Y:62 Z:-639", 569, 62, -639),
    CLUE_34(34, "OHRUKA ECDOI", "Duke Horacio", "X:576 Y:71 Z:185", 576, 71, 185),
    CLUE_35(35, "RDRRIS ODIWZAE", "Wizard Sedridor", "X:284 Y:49 Z:376", 284, 49, 376),
    CLUE_36(36, "TAFEERCKARNE", "Father Aereck", "X:687 Y:64 Z:228", 687, 64, 228),
    CLUE_37(37, "RAMIK", "Karim", "X:751 Y:66 Z:292", 751, 66, 292);

    private final int id;
    private final String clueStep;
    private final String answer;
    private final String coords;
    private final BlockPos blockPos;

    ClueScrollClue(int id, String clueStep, String answer, String coords, int x, int y, int z) {
        this.id = id;
        this.clueStep = clueStep;
        this.answer = answer;
        this.coords = coords;
        this.blockPos = new BlockPos(x, y, z);
    }

    public int id() { return this.id; }
    public String displayId() { return Integer.toString(this.id); }
    public String clueStep() { return this.clueStep; }
    public String answer() { return this.answer; }
    public String coords() { return this.coords; }
    public BlockPos blockPos() { return this.blockPos; }

    static ClueScrollClue match(String text) {
        if (text == null || text.isBlank()) return null;
        String normalized = normalize(text);
        for (ClueScrollClue clue : values()) {
            if (!clue.clueStep.isBlank() && normalized.contains(normalize(clue.clueStep))) {
                return clue;
            }
        }
        for (ClueScrollClue clue : values()) {
            if (!clue.answer.isBlank() && normalized.contains(normalize(clue.answer))) {
                return clue;
            }
        }
        for (ClueScrollClue clue : values()) {
            if (normalized.matches(".*\\b(?:clue|step|id)?\\s*#?" + clue.id + "\\b.*")) {
                return clue;
            }
        }
        return null;
    }

    private static String normalize(String text) {
        return text.replaceAll("(?i)\\u00A7[0-9A-FK-OR]", "")
            .replaceAll("\\s+", " ")
            .trim()
            .toLowerCase(Locale.ROOT);
    }
}
