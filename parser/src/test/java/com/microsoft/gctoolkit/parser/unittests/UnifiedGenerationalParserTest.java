// Copyright (c) Microsoft Corporation.
// Licensed under the MIT License.
package com.microsoft.gctoolkit.parser.unittests;

import com.microsoft.gctoolkit.parser.diary.TestLogFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.fail;

public class UnifiedGenerationalParserTest extends ParserTest {

    @Test
    public void testForDetailsLogs() {
        int i = 0;
        for (String name : details) {
            try {
                Path path = new TestLogFile("parallel/" + name).getFile().toPath();
                TestResults testResults = testGenerationalSingleLogFile(path);
                analyzeResults(name, testResults, detailsNumberOfDifferentCollectors[i], detailsCounts[i++]);
            } catch (IOException ioe) {
                fail(ioe.getMessage());
            }
        }
    }

    private static final String[] details = {
            "parallelgc.log"
    };

    private static final int[] detailsNumberOfDifferentCollectors = {
            2,
    };

    private static final int[][] detailsCounts = {
            //  0,   1,   2,    3,    4,    5,    6,    7,    8,    9,    10,    11,    12
            {   0,   0,   0,    0,    0,    0,    0, 4638,   28,    0,     0,     0,     0},
    };

    @Test
    public void testCMSLogsMissingTimeStamps() {
        int i = 0;
        for (String name : cms) {
            try {
                Path path = new TestLogFile("cms/" + name).getFile().toPath();
                TestResults testResults = testGenerationalSingleLogFile(path);
                analyzeResults(name, testResults, cmsNumberOfDifferentCollectors[i], cmsCounts[i++]);
            } catch (IOException ioe) {
                fail(ioe.getMessage());
            }
        }
    }

    private static final String[] cms = {
            "no_timestamps.log"
    };

    private static final int[] cmsNumberOfDifferentCollectors = {
            4,
    };

    private static final int[][] cmsCounts = {
            //  0,   1,    2,     3,     4,     5,     6,    7,    8,     9,      10,     11,      12
            {   0,   0, 4461,     0,     0,     2,     0,    0,    0,     0,       0,      2,       2},
    };
}
