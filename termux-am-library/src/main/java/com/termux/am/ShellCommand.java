/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.termux.am;

/**
 * Copied from android-7.0.0_r1 frameworks/base/core/java/android/os
 */
public class ShellCommand {

    static final String TAG = "ShellCommand";

    static final boolean DEBUG = false;

    private String[] mArgs;

    private int mArgPos;

    private String mCurArgData;

    public void init(String[] args, int firstArgPos) {
        mArgs = args;
        mArgPos = firstArgPos;
        mCurArgData = null;
    }

    /**
     * Return the next option on the command line -- that is an argument that
     * starts with '-'.  If the next argument is not an option, null is returned.
     */
    public String getNextOption() {
        if (mCurArgData != null) {
            String prev = mArgs[mArgPos - 1];
            throw new IllegalArgumentException("No argument expected after \"" + prev + "\"");
        }
        if (mArgPos >= mArgs.length) {
            return null;
        }
        String arg = mArgs[mArgPos];
        if (!arg.startsWith("-")) {
            return null;
        }
        mArgPos++;
        if (arg.equals("--")) {
            return null;
        }
        if (arg.length() > 1 && arg.charAt(1) != '-') {
            if (arg.length() > 2) {
                mCurArgData = arg.substring(2);
                return arg.substring(0, 2);
            } else {
                mCurArgData = null;
                return arg;
            }
        }
        mCurArgData = null;
        return arg;
    }

    /**
     * Return the next argument on the command line, whatever it is; if there are
     * no arguments left, return null.
     */
    public String getNextArg() {
        if (mCurArgData != null) {
            String arg = mCurArgData;
            mCurArgData = null;
            return arg;
        } else if (mArgPos < mArgs.length) {
            return mArgs[mArgPos++];
        } else {
            return null;
        }
    }

    public String peekNextArg() {
        if (mCurArgData != null) {
            return mCurArgData;
        } else if (mArgPos < mArgs.length) {
            return mArgs[mArgPos];
        } else {
            return null;
        }
    }

    /**
     * Return the next argument on the command line, whatever it is; if there are
     * no arguments left, throws an IllegalArgumentException to report this to the user.
     */
    public String getNextArgRequired() {
        String arg = getNextArg();
        if (arg == null) {
            String prev = mArgs[mArgPos - 1];
            throw new IllegalArgumentException("Argument expected after \"" + prev + "\"");
        }
        return arg;
    }
}
