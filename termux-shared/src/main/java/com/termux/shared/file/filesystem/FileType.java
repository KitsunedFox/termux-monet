package com.termux.shared.file.filesystem;

/**
 * The {@link Enum} that defines file types.
 */
public enum FileType {

    // 00000000
    NO_EXIST("no exist", 0),
    // 00000001
    REGULAR("regular", 1),
    // 00000010
    DIRECTORY("directory", 2),
    // 00000100
    SYMLINK("symlink", 4),
    // 00001000
    SOCKET("socket", 8),
    // 00010000
    CHARACTER("character", 16),
    // 00100000
    FIFO("fifo", 32),
    // 01000000
    BLOCK("block", 64),
    // 10000000
    UNKNOWN("unknown", 128);

    private final String name;

    private final int value;

    FileType(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
