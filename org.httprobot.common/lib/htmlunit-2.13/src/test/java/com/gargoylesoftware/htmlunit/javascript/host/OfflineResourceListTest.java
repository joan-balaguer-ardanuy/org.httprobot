/*
 * Copyright (c) 2002-2013 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript.host;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.WebDriverTestCase;

/**
 * Unit tests for {@link OfflineResourceList}.
 *
 * @version $Revision: 8619 $
 * @author Daniel Gredler
 * @author Frank Danek
 */
@RunWith(BrowserRunner.class)
public class OfflineResourceListTest extends WebDriverTestCase {

    /**
     * @throws Exception if an error occurs
     */
    @Test
    @Alerts(FF = "[object OfflineResourceList]",
            IE = "undefined",
            IE10 = "[object ApplicationCache]")
    public void existence() throws Exception {
        final String html
            = "<html><body><script>\n"
            + "alert(window.applicationCache);\n"
            + "</script></body></html>";
        loadPageWithAlerts2(html);
    }

}
