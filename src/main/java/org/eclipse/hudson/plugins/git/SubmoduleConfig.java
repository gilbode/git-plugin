/*******************************************************************************
 *
 * Copyright (c) 2011 Oracle Corporation.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 * Andrew Bayer, Anton Kozak, Nikita Levyankov
 *
 *******************************************************************************/

package org.eclipse.hudson.plugins.git;

import java.util.regex.Pattern;

public class SubmoduleConfig implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    String submoduleName;
    String[] branches;

    public String getSubmoduleName() {
        return submoduleName;
    }

    public void setSubmoduleName(String submoduleName) {
        this.submoduleName = submoduleName;
    }

    public String[] getBranches() {
        return branches;
    }

    public void setBranches(String[] branches) {
        this.branches = branches;
    }

    public boolean revisionMatchesInterest(Revision r) {
        for (Branch br : r.getBranches()) {
            if (branchMatchesInterest(br)) {
                return true;
            }
        }
        return false;
    }

    public boolean branchMatchesInterest(Branch br) {
        for (String regex : branches) {
            if (!Pattern.matches(regex, br.getName())) {
                return false;
            }
        }
        return true;
    }

    public String getBranchesString() {
        String ret = "";

        for (String branch : branches) {
            if (ret.length() > 0) {
                ret += ",";
            }
            ret += branch;
        }
        return ret;

    }
}
