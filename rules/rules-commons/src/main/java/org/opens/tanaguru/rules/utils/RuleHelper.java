/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.utils;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * This class is a utility class
 *
 */
public abstract class RuleHelper {

    /**
     *
     * @param testSolutionCollection
     *            the test solution collection to synthesize
     * @return the synthesis of the test solution collection
     */
    public static TestSolution synthesizeTestSolutionCollection(Collection<TestSolution> testSolutionCollection) {
        boolean hasPassed = false;
        boolean hasFailed = false;
        boolean hasNMI = false;
        for (TestSolution result : testSolutionCollection) {
            switch (result) {
                case PASSED:
                    hasPassed = true;
                    break;
                case FAILED:
                    hasFailed = true;
                    break;
                case NEED_MORE_INFO:
                    hasNMI = true;
                    break;
                default:
            }
        }

        if (hasFailed) {
            return TestSolution.FAILED;
        }
        if (hasNMI) {
            return TestSolution.NEED_MORE_INFO;
        }
        if (hasPassed) {
            return TestSolution.PASSED;
        }
        return TestSolution.NOT_APPLICABLE;
    }

    /**
     *
     * @param processResultCollection
     *            the process result collection to synthesize
     * @return the synthesis of the process result collection
     */
    public static TestSolution synthesizeProcessResultCollection(
            Collection<ProcessResult> processResultCollection) {
        boolean hasPassed = false;
        boolean hasFailed = false;
        boolean hasNMI = false;
        for (ProcessResult testResult : processResultCollection) {
            DefiniteResult definiteResult = (DefiniteResult) testResult;
            switch (definiteResult.getDefiniteValue()) {
                case PASSED:
                    hasPassed = true;
                    break;
                case FAILED:
                    hasFailed = true;
                    break;
                case NEED_MORE_INFO:
                    hasNMI = true;
                    break;
                default:
            }
        }

        if (hasFailed) {
            return TestSolution.FAILED;
        }
        if (hasNMI) {
            return TestSolution.NEED_MORE_INFO;
        }
        if (hasPassed) {
            return TestSolution.PASSED;
        }
        return TestSolution.NOT_APPLICABLE;
    }
    
}