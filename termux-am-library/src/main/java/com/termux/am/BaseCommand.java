/*
**
** Copyright 2013, The Android Open Source Project
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**     http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
package com.termux.am;

import java.io.PrintStream;

/**
 * Copied from android-7.0.0_r1 frameworks/base/core/java/com/android/internal/os
 */
public abstract class BaseCommand {

    final protected ShellCommand mArgs = new ShellCommand();

    // These are magic strings understood by the Eclipse plugin.
    public static final String FATAL_ERROR_CODE = "Error type 1";

    public static final String NO_SYSTEM_ERROR_CODE = "Error type 2";

    public static final String NO_CLASS_ERROR_CODE = "Error type 3";

    protected PrintStream out;

    protected PrintStream err;

    public BaseCommand(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    /**
     * Call to run the command.
     */
    public int run(String[] args) {
        if (args.length < 1) {
            onShowUsage(out);
            return 1;
        }
        mArgs.init(args, 0);
        try {
            onRun();
        } catch (IllegalArgumentException e) {
            onShowUsage(err);
            err.println();
            err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(err);
            return 1;
        }
        return 0;
    }

    /**
     * Convenience to show usage information to error output.
     */
    public void showUsage() {
        onShowUsage(err);
    }

    /**
     * Convenience to show usage information to error output along
     * with an error message.
     */
    public void showError(String message) {
        onShowUsage(err);
        err.println();
        err.println(message);
    }

    /**
     * Implement the command.
     */
    public abstract void onRun() throws Exception;

    /**
     * Print help text for the command.
     */
    public abstract void onShowUsage(PrintStream out);

    /**
     * Return the next option on the command line -- that is an argument that
     * starts with '-'.  If the next argument is not an option, null is returned.
     */
    public String nextOption() {
        return mArgs.getNextOption();
    }

    /**
     * Return the next argument on the command line, whatever it is; if there are
     * no arguments left, return null.
     */
    public String nextArg() {
        return mArgs.getNextArg();
    }

    /**
     * Return the next argument on the command line, whatever it is; if there are
     * no arguments left, throws an IllegalArgumentException to report this to the user.
     */
    public String nextArgRequired() {
        return mArgs.getNextArgRequired();
    }
}
