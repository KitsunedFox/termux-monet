package com.termux.terminal;

/**
 * <pre>
 * "CSI ? Pm h", DEC Private Mode Set (DECSET)
 * </pre>
 * <p/>
 * and
 * <p/>
 * <pre>
 * "CSI ? Pm l", DEC Private Mode Reset (DECRST)
 * </pre>
 * <p/>
 * controls various aspects of the terminal
 */
public class DecSetTest extends TerminalTestCase {

    /**
     * DECSET 25, DECTCEM, controls visibility of the cursor.
     */
    public void testEnableDisableCursor() {
        withTerminalSized(3, 3);
        assertTrue("Initially the cursor should be enabled", mTerminal.isCursorEnabled());
        // Disable Cursor (DECTCEM).
        enterString("\033[?25l");
        assertFalse(mTerminal.isCursorEnabled());
        // Enable Cursor (DECTCEM).
        enterString("\033[?25h");
        assertTrue(mTerminal.isCursorEnabled());
        // Disable Cursor (DECTCEM), again.
        enterString("\033[?25l");
        assertFalse(mTerminal.isCursorEnabled());
        mTerminal.reset();
        assertTrue("Resetting the terminal should enable the cursor", mTerminal.isCursorEnabled());
        enterString("\033[?25l");
        assertFalse(mTerminal.isCursorEnabled());
        // RIS resetting should enabled cursor.
        enterString("\033c");
        assertTrue(mTerminal.isCursorEnabled());
    }

    /**
     * DECSET 2004, controls bracketed paste mode.
     */
    public void testBracketedPasteMode() {
        withTerminalSized(3, 3);
        mTerminal.paste("a");
        assertEquals("Pasting 'a' should output 'a' when bracketed paste mode is disabled", "a", mOutput.getOutputAndClear());
        // Enable bracketed paste mode.
        enterString("\033[?2004h");
        mTerminal.paste("a");
        assertEquals("Pasting when in bracketed paste mode should be bracketed", "\033[200~a\033[201~", mOutput.getOutputAndClear());
        // Disable bracketed paste mode.
        enterString("\033[?2004l");
        mTerminal.paste("a");
        assertEquals("Pasting 'a' should output 'a' when bracketed paste mode is disabled", "a", mOutput.getOutputAndClear());
        // Enable bracketed paste mode, again.
        enterString("\033[?2004h");
        mTerminal.paste("a");
        assertEquals("Pasting when in bracketed paste mode again should be bracketed", "\033[200~a\033[201~", mOutput.getOutputAndClear());
        mTerminal.paste("\033ab\033cd\033");
        assertEquals("Pasting an escape character should not input it", "\033[200~abcd\033[201~", mOutput.getOutputAndClear());
        mTerminal.paste("\u0081ab\u0081cd\u009F");
        assertEquals("Pasting C1 control codes should not input it", "\033[200~abcd\033[201~", mOutput.getOutputAndClear());
        mTerminal.reset();
        mTerminal.paste("a");
        assertEquals("Terminal reset() should disable bracketed paste mode", "a", mOutput.getOutputAndClear());
    }

    /**
     * DECSET 7, DECAWM, controls wraparound mode.
     */
    public void testWrapAroundMode() {
        // Default with wraparound:
        withTerminalSized(3, 3).enterString("abcd").assertLinesAre("abc", "d  ", "   ");
        // With wraparound disabled:
        withTerminalSized(3, 3).enterString("\033[?7labcd").assertLinesAre("abd", "   ", "   ");
        enterString("efg").assertLinesAre("abg", "   ", "   ");
        // Re-enabling wraparound:
        enterString("\033[?7hhij").assertLinesAre("abh", "ij ", "   ");
    }
}
