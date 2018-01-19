package com.ishowtree.stickyheaderview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by showhome002 on 2018/1/19.
 */

public class DataUtil {

    public static final int MODEL_COUNT = 30;

    public static List<StickyExampleModel> getData() {
        List<StickyExampleModel> stickyExampleModels = new ArrayList<>();

        for (int index = 0; index < MODEL_COUNT; index++) {
            if (index < 5) {
                stickyExampleModels.add(new StickyExampleModel(
                        "吸顶文本1", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 15) {
                stickyExampleModels.add(new StickyExampleModel(
                        "吸顶文本2", "name" + index, "gender" + index, "profession" + index));
            } else if (index < 25) {
                stickyExampleModels.add(new StickyExampleModel(
                        "吸顶文本3", "name" + index, "gender" + index, "profession" + index));
            } else {
                stickyExampleModels.add(new StickyExampleModel(
                        "吸顶文本4", "name" + index, "gender" + index, "profession" + index));
            }
        }

        return stickyExampleModels;
    }
}
