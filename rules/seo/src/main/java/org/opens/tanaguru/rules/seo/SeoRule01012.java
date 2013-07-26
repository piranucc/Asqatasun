/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
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
package org.opens.tanaguru.rules.seo;

import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.seo.detection.AbstractTagDetectionPageRuleImplementation;

/**
 * Test whether the meta description is relevant on the page
 * 
 * @author jkowalczyk
 */
public class SeoRule01012 extends AbstractTagDetectionPageRuleImplementation {

    public static final String ERROR_MESSAGE_CODE = "EmptyMetaDescriptionTag";
    public static final String CHECK_ELEMENT_MESSAGE_CODE = "checkRelevancyMetaDescriptionTag";
    private static final String TAG_DETECTION_CSS_EXPR =
            "head meta[name=description][content]";
    public static final String META_DESC_EVIDENCE_NAME = "MetaDescription";
    public static final String MORE_THAN_ONE_META_DESC_MSG_CODE = "MoreThanOneMetaDescriptionTag";

    public SeoRule01012() {
        super();
        setMessageCode(ERROR_MESSAGE_CODE);
        setSelectionExpression(TAG_DETECTION_CSS_EXPR);
        setDetectedSolution(TestSolution.NEED_MORE_INFO);
        setNotDetectedSolution(TestSolution.NOT_APPLICABLE);
        setIsRemarkCreatedOnDetection(true);
        setHasElementToBeUnique(true);
        setHasElementToBeNotNullAndContainsAlphanumericalContent(true);
        setElementName("content");
        setNotUniqueMessage(MORE_THAN_ONE_META_DESC_MSG_CODE);
        setNotUniqueEvidenceElement(META_DESC_EVIDENCE_NAME);
        setCheckElementMessageCode(CHECK_ELEMENT_MESSAGE_CODE);
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Elements elements = sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(TAG_DETECTION_CSS_EXPR).
                getSelectedElements();
        if (elements.isEmpty() || elements.size() > 1) {
            return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                TestSolution.NOT_APPLICABLE,
                sspHandler.getRemarkList());
        } else {
            return super.processImpl(sspHandler);
        }
    }

}