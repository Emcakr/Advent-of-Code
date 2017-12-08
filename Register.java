import java.util.ArrayList;

public class Register {
    String[] registerNames;
    int[]  registerValues;
    String[] instructionTarget;
    char[] operation;
    int[] change;
    String[][] conditions;
    private int highestNumberEver;

    Register(String input){
        highestNumberEver=0;
        String[] lines = input.split("\n");
        String[][] table = new String[lines.length][];

        for (int i = 0; i < table.length; i++) {
            table[i] = lines[i].split(" ");
        }

        boolean inList;
        ArrayList<String> nameCollector =  new ArrayList<>();
        instructionTarget = new String[table.length];
        operation = new char[table.length];
        change = new int[table.length];
        conditions = new String[table.length][3];


        for (int i = 0; i < table.length; i++) {
            inList = false;
            for (int j = 0; j < nameCollector.size(); j++) {
                if (table[i][0].equals(nameCollector.get(j))){
                    inList = true;
                    break;
                }
            }
            if (!inList){
                nameCollector.add(table[i][0]);
            }
            inList = false;
            for (int j = 0; j < nameCollector.size(); j++) {
                if (table[i][4].equals(nameCollector.get(j))){
                    inList = true;
                    break;
                }
            }
            if (!inList){
                nameCollector.add(table[i][4]);
            }

            instructionTarget[i] = table[i][0];

            if (table[i][1].equals("inc")){
                operation[i]='+';
            } else {
                operation[i]='-';
            }

            change[i] = Integer.parseInt(table[i][2]);

            for (int j = 0; j < 3; j++) {
                conditions[i][j] = table[i][j+4];
            }
        }
        registerNames = new String[nameCollector.size()];

        for (int i = 0; i < registerNames.length; i++) {
            registerNames[i] = nameCollector.get(i);
        }

        registerValues =  new int[registerNames.length];

    }

    int findRegisterByName(String name){
        for (int i = 0; i < registerNames.length; i++) {
            if (registerNames[i].equals(name)){
                return i;
            }
        }

        return -1;
    }

    int valueOfNamedRegister(String name){
        return registerValues[findRegisterByName(name)];
    }

    String nameRegister(int number){
        return registerNames[number];
    }

    int valueOfHighestRegister(){
        int highest = registerValues[0];
        for (int i = 1; i < registerValues.length; i++) {
            if (registerValues[i]>highest) {
                highest = registerValues[i];
            }
        }
        return highest;
    }

    void doStep(int step){
        int target = findRegisterByName(instructionTarget[step]);
        int delta = change[step];

        switch (operation[step]){
            case'+':
                registerValues[target] += delta;
                break;
            case '-':
                registerValues[target] -= delta;
        }
        if(registerValues[target]>highestNumberEver){
            highestNumberEver=registerValues[target];
        }
    }

    int  getHighestNumberEver(){
        return highestNumberEver;
    }


    void runInstructions(){
        for (int i = 0; i < conditions.length; i++) {
            int referenceValue = registerValues[findRegisterByName(conditions[i][0])];
            int checkAgainst = Integer.parseInt(conditions[i][2]);

            switch (conditions[i][1]){
                case "!=":
                    if (referenceValue!=checkAgainst){
                        doStep(i);
                    }
                    break;
                case "==":
                    if (referenceValue==checkAgainst){
                        doStep(i);
                    }
                    break;
                case ">=":
                    if (referenceValue>=checkAgainst){
                        doStep(i);
                    }
                    break;
                case "<=":
                    if (referenceValue<=checkAgainst){
                        doStep(i);
                    }
                    break;
                case ">":
                    if (referenceValue>checkAgainst){
                        doStep(i);
                    }
                    break;
                case "<":
                    if (referenceValue<checkAgainst){
                        doStep(i);
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Register register = new Register("q inc -541 if c != 4\n" +
                "s inc -555 if o > -5\n" +
                "px dec -84 if x >= -4\n" +
                "y dec -822 if txk > -1\n" +
                "wdc inc -731 if tup < -5\n" +
                "ug dec -943 if s != -551\n" +
                "rz inc 468 if j > 1\n" +
                "rz dec 628 if j >= -3\n" +
                "zeq inc -801 if gr >= 7\n" +
                "jb dec 592 if px > 80\n" +
                "q inc -151 if rz <= -628\n" +
                "l dec -423 if b <= 7\n" +
                "vhu inc 904 if q >= -700\n" +
                "b dec 438 if kyh == 0\n" +
                "o inc 491 if vhu < 908\n" +
                "l inc 761 if tup >= 8\n" +
                "kq inc -790 if kyh > -9\n" +
                "tup dec 171 if vhu != 909\n" +
                "y dec 473 if px != 81\n" +
                "kq inc 689 if jb < -590\n" +
                "vhu inc -802 if o > 488\n" +
                "jb inc 165 if l >= 418\n" +
                "kps inc 49 if s >= -564\n" +
                "kps dec 508 if ug == 943\n" +
                "txk dec 352 if djl > -2\n" +
                "axi dec -4 if y <= 347\n" +
                "rz inc -40 if x <= -4\n" +
                "xhe inc -538 if l < 428\n" +
                "l dec 396 if kq < -97\n" +
                "djl inc -462 if rz < -619\n" +
                "xhe dec -173 if vhu > 98\n" +
                "ug dec 227 if jb < -417\n" +
                "x dec 754 if q == -692\n" +
                "kps inc 671 if gr < 10\n" +
                "ug inc 722 if o > 490\n" +
                "o inc 743 if s == -562\n" +
                "rz inc -427 if mp >= -2\n" +
                "s dec -630 if j < 0\n" +
                "vhu inc -413 if kyh > -8\n" +
                "kyh dec -389 if djl != -458\n" +
                "wdc inc 593 if ug >= 1432\n" +
                "o inc 988 if kq != -101\n" +
                "b inc -550 if c > -5\n" +
                "c inc -390 if q != -701\n" +
                "axi inc 450 if wdc <= 586\n" +
                "xhe dec 840 if kyh > 385\n" +
                "mp inc -789 if vhu > -313\n" +
                "wdc dec -387 if j <= 1\n" +
                "kps dec 625 if wdc != 980\n" +
                "q inc 40 if b <= -979\n" +
                "gr inc 164 if b > -982\n" +
                "ug inc 124 if kyh == 389\n" +
                "rz inc -893 if axi != 8\n" +
                "b dec 428 if x <= -757\n" +
                "vhu dec -205 if o != 500\n" +
                "ug inc -375 if q < -644\n" +
                "djl inc -954 if jb >= -434\n" +
                "px inc 170 if kyh <= 396\n" +
                "jb dec -844 if mp > -796\n" +
                "px dec -473 if c != -390\n" +
                "rz dec 480 if x > -759\n" +
                "kyh inc -698 if l <= 32\n" +
                "q dec -185 if x <= -753\n" +
                "c dec -765 if kps > 210\n" +
                "mp dec 207 if l < 28\n" +
                "l inc -870 if kyh <= -309\n" +
                "kyh inc -323 if s <= -559\n" +
                "x dec 805 if axi == 0\n" +
                "q inc 376 if kq == -101\n" +
                "x inc 550 if s < -550\n" +
                "x dec 647 if kps == 212\n" +
                "zeq inc -839 if rz >= -2436\n" +
                "djl dec -759 if l > -850\n" +
                "tup inc -551 if o > 481\n" +
                "vhu inc 12 if kq != -101\n" +
                "kq dec -953 if px == 254\n" +
                "c inc 523 if djl >= -657\n" +
                "kyh dec -524 if c == 898\n" +
                "tup dec 478 if px == 254\n" +
                "xhe inc 301 if q == -91\n" +
                "kyh dec -638 if y == 350\n" +
                "l inc -412 if rz <= -2421\n" +
                "ug dec 548 if kps > 207\n" +
                "djl dec -830 if mp <= -997\n" +
                "tup inc 367 if gr <= 0\n" +
                "ug dec 997 if tup < -824\n" +
                "mp dec -253 if txk >= -356\n" +
                "kq inc 204 if jb >= 415\n" +
                "px inc 567 if jb < 410\n" +
                "kps dec 306 if txk == -353\n" +
                "kps dec -171 if kyh > 222\n" +
                "djl inc -833 if xhe <= -902\n" +
                "kyh dec 648 if vhu >= -114\n" +
                "djl inc -903 if kq < 1051\n" +
                "kps inc -940 if djl < -1489\n" +
                "y dec -408 if j <= -10\n" +
                "mp dec -886 if o == 491\n" +
                "rz inc 571 if l >= -1246\n" +
                "s dec -178 if kq < 1062\n" +
                "kq dec 718 if jb < 421\n" +
                "axi dec 587 if kps <= -721\n" +
                "jb inc -879 if j != 0\n" +
                "tup dec 632 if zeq > -843\n" +
                "tup dec -673 if s != -378\n" +
                "kq dec -88 if vhu > -114\n" +
                "jb dec 74 if o <= 492\n" +
                "ug inc -716 if s <= -387\n" +
                "rz inc 265 if zeq > -842\n" +
                "axi inc -995 if l != -1246\n" +
                "px inc 481 if gr <= -1\n" +
                "x dec -811 if axi < -1590\n" +
                "y inc 375 if s != -386\n" +
                "wdc dec -484 if tup < -790\n" +
                "c inc -335 if xhe == -908\n" +
                "c inc -80 if px == 254\n" +
                "c inc 263 if kq == 426\n" +
                "gr inc 259 if kq > 429\n" +
                "tup dec -749 if txk == -356\n" +
                "jb inc 399 if wdc >= 1455\n" +
                "mp inc -334 if axi <= -1577\n" +
                "b inc 442 if zeq <= -836\n" +
                "px inc -890 if xhe >= -912\n" +
                "zeq inc -41 if o != 488\n" +
                "ug dec 781 if x >= -1654\n" +
                "wdc dec 246 if px > -645\n" +
                "gr dec -707 if djl <= -1482\n" +
                "wdc dec -90 if y <= 730\n" +
                "y inc 674 if o != 491\n" +
                "j dec -911 if vhu <= -102\n" +
                "jb dec 575 if y == 724\n" +
                "rz dec -10 if c <= 1088\n" +
                "axi dec 272 if axi != -1586\n" +
                "gr dec -678 if s != -377\n" +
                "kyh inc -768 if gr == 707\n" +
                "s inc 499 if mp > -195\n" +
                "zeq inc 574 if wdc <= 1317\n" +
                "axi dec -339 if x != -1647\n" +
                "o dec -884 if xhe >= -905\n" +
                "mp inc 718 if c >= 1073\n" +
                "l inc 101 if px == -636\n" +
                "djl dec -7 if y != 723\n" +
                "wdc dec 997 if b != -537\n" +
                "gr dec -155 if jb >= 176\n" +
                "tup dec -403 if kyh < -1201\n" +
                "kps dec -240 if djl <= -1480\n" +
                "c dec -786 if mp > 525\n" +
                "px inc -452 if ug >= -365\n" +
                "x inc -527 if vhu >= -113\n" +
                "zeq inc 524 if b != -552\n" +
                "djl inc -429 if mp != 528\n" +
                "c dec -667 if o >= 1368\n" +
                "o inc 724 if x >= -2185\n" +
                "gr dec 568 if kyh < -1195\n" +
                "axi inc -571 if j <= 904\n" +
                "px inc 423 if b >= -547\n" +
                "txk inc -561 if q <= -87\n" +
                "tup dec 382 if kps < -487\n" +
                "gr dec 18 if px > -674\n" +
                "xhe dec -981 if y > 721\n" +
                "tup dec -68 if txk > -908\n" +
                "q inc 583 if axi < -1508\n" +
                "gr dec -404 if s != 122\n" +
                "ug inc 743 if c == 2534\n" +
                "xhe inc 985 if c <= 2537\n" +
                "axi dec -258 if b != -538\n" +
                "o dec 994 if gr >= 113\n" +
                "vhu inc -278 if tup != -1181\n" +
                "djl inc 489 if djl == -1912\n" +
                "axi dec 580 if kps <= -490\n" +
                "ug dec -803 if xhe >= 1055\n" +
                "kps inc 156 if mp != 526\n" +
                "zeq inc 214 if mp >= 518\n" +
                "vhu inc 830 if mp < 536\n" +
                "b dec 249 if b > -549\n" +
                "o inc -626 if rz == -2153\n" +
                "jb dec -220 if x > -2193\n" +
                "djl dec -546 if j >= 902\n" +
                "px dec 964 if px >= -669\n" +
                "tup dec -719 if kps > -326\n" +
                "gr inc -555 if s >= 132\n" +
                "j dec -922 if o >= 485\n" +
                "j dec -112 if q != 482\n" +
                "tup inc -463 if s <= 118\n" +
                "jb dec -195 if o >= 472\n" +
                "x dec -973 if gr == 121\n" +
                "x inc 253 if axi == -1247\n" +
                "o inc 818 if wdc == 311\n" +
                "gr dec 687 if tup != -1171\n" +
                "s inc -869 if vhu <= 450\n" +
                "vhu dec 288 if l < -1149\n" +
                "rz dec 703 if xhe != 1067\n" +
                "rz dec 700 if o >= 1293\n" +
                "txk inc -983 if zeq >= 440\n" +
                "kq inc -364 if q != 492\n" +
                "kq inc 789 if x == -1201\n" +
                "px dec 560 if x <= -1208\n" +
                "wdc inc 562 if s == -741\n" +
                "j inc -665 if kyh != -1198\n" +
                "s inc 332 if kps >= -333\n" +
                "mp dec 411 if rz < -3552\n" +
                "axi dec 504 if wdc != 311\n" +
                "kps dec 56 if ug > 1182\n" +
                "jb inc 497 if px > -2191\n" +
                "axi dec -364 if q < 501\n" +
                "kq dec -308 if x < -1211\n" +
                "zeq dec 304 if q >= 489\n" +
                "kyh dec -957 if x >= -1211\n" +
                "tup dec -670 if l > -1145\n" +
                "jb dec -166 if o <= 1297\n" +
                "l dec -900 if s <= -406\n" +
                "txk inc 917 if px >= -2182\n" +
                "rz inc 426 if y == 724\n" +
                "kyh inc -993 if j > 353\n" +
                "y dec -513 if kq != 425\n" +
                "zeq inc 329 if l >= -251\n" +
                "kps dec 460 if mp < 121\n" +
                "wdc dec 492 if xhe != 1062\n" +
                "djl dec -945 if gr <= -560\n" +
                "px inc 368 if wdc == 311\n" +
                "zeq inc 250 if y != 1247\n" +
                "wdc dec 182 if y < 1237\n" +
                "txk dec 577 if rz <= -3128\n" +
                "b inc -252 if l < -247\n" +
                "j dec 173 if px == -1821\n" +
                "djl inc -858 if px == -1821\n" +
                "mp dec 609 if wdc != 321\n" +
                "wdc inc -723 if kyh != -1230\n" +
                "kyh dec -172 if kyh < -1229\n" +
                "wdc dec -702 if ug < 1187\n" +
                "px inc -276 if jb <= 1245\n" +
                "l dec -208 if txk > -1483\n" +
                "y dec 319 if kps < -850\n" +
                "kyh inc -597 if kps != -848\n" +
                "c dec 161 if tup == -1170\n" +
                "kps inc 320 if o <= 1302\n" +
                "rz inc 972 if px == -2097\n" +
                "kyh dec 483 if rz >= -2165\n" +
                "kps dec -845 if q <= 494\n" +
                "kyh dec 268 if o == 1301\n" +
                "jb dec 835 if ug >= 1192\n" +
                "kq inc 93 if vhu != 158\n" +
                "vhu inc -693 if q != 490\n" +
                "x dec -75 if txk <= -1484\n" +
                "jb dec 419 if jb >= 1243\n" +
                "b dec -84 if x >= -1137\n" +
                "xhe inc -156 if kq <= 424\n" +
                "s dec 653 if o >= 1297\n" +
                "kyh inc 880 if c >= 2530\n" +
                "wdc inc 295 if xhe > 1061\n" +
                "y dec 140 if xhe != 1062\n" +
                "txk dec 28 if wdc == -117\n" +
                "kps dec 640 if rz >= -2166\n" +
                "x dec 369 if y > 1232\n" +
                "kps inc 232 if l >= -255\n" +
                "b dec 89 if gr < -566\n" +
                "wdc inc -217 if b <= -954\n" +
                "vhu inc -29 if zeq <= 385\n" +
                "kyh inc 861 if zeq <= 370\n" +
                "rz inc 425 if jb == 826\n" +
                "ug inc -857 if ug >= 1189\n" +
                "mp inc -418 if x != -1505\n" +
                "jb dec -815 if kq == 426\n" +
                "ug inc -584 if xhe >= 1055\n" +
                "kyh dec 216 if y <= 1240\n" +
                "txk inc 106 if s <= -1064\n" +
                "gr inc -775 if xhe > 1063\n" +
                "o inc 647 if l == -254\n" +
                "djl inc -461 if kq == 426\n" +
                "s dec 962 if x >= -1512\n" +
                "s dec -721 if y < 1230\n" +
                "rz dec 289 if vhu == -567\n" +
                "kq inc 651 if kq < 432\n" +
                "px inc 371 if vhu <= -564\n" +
                "axi dec -325 if kq > 1071\n" +
                "b inc -971 if s != -2030\n" +
                "c dec -526 if zeq >= 380\n" +
                "zeq dec 239 if jb <= 1643\n" +
                "kq inc -757 if kps != -87\n" +
                "q dec 436 if y < 1241\n" +
                "kq dec -557 if kps < -81\n" +
                "s dec 115 if y >= 1236\n" +
                "s inc -134 if c < 2543\n" +
                "q inc -304 if txk <= -1407\n" +
                "xhe dec 975 if rz == -1733\n" +
                "tup dec 814 if rz >= -1742\n" +
                "l inc 969 if ug == 604\n" +
                "vhu dec 625 if wdc < -333\n" +
                "xhe inc -406 if zeq != 136\n" +
                "xhe inc 448 if jb >= 1640\n" +
                "c dec -894 if q > -249\n" +
                "b dec -778 if axi == -568\n" +
                "zeq inc 966 if o <= 1953\n" +
                "zeq dec 148 if wdc <= -334\n" +
                "mp inc 812 if zeq <= 958\n" +
                "zeq inc -295 if kyh != -878\n" +
                "j dec -719 if axi >= -574\n" +
                "tup inc -59 if c > 3425\n" +
                "txk inc 538 if jb == 1641\n" +
                "zeq dec 854 if jb <= 1644\n" +
                "mp dec 735 if o <= 1949\n" +
                "axi dec 750 if c >= 3421\n" +
                "gr dec 633 if axi >= -1321\n" +
                "x dec 626 if mp >= -827\n" +
                "xhe inc 138 if txk <= -872\n" +
                "axi inc -838 if txk < -866\n" +
                "px inc 571 if tup >= -2045\n" +
                "jb dec 793 if px >= -1730\n" +
                "l inc -759 if zeq == -202\n" +
                "kyh inc -923 if kq <= 881\n" +
                "tup dec 903 if b <= -181\n" +
                "o dec -958 if l != 707\n" +
                "xhe inc 814 if x == -1504\n" +
                "o inc 454 if o != 2906\n" +
                "djl inc 469 if jb == 848\n" +
                "mp dec -114 if q != -240\n" +
                "y dec -393 if djl < -776\n" +
                "ug inc -365 if mp > -713\n" +
                "tup dec -254 if gr == -1199\n" +
                "rz dec 587 if gr > -1203\n" +
                "px dec -611 if b > -191\n" +
                "txk inc -488 if kyh <= -1800\n" +
                "axi dec 834 if c <= 3437\n" +
                "tup inc -946 if gr <= -1199\n" +
                "x inc -225 if wdc < -342\n" +
                "j inc 526 if s < -2273\n" +
                "ug dec -654 if c != 3427\n" +
                "mp dec -521 if s <= -2274\n" +
                "vhu dec -319 if txk >= -1352\n" +
                "c inc 615 if gr > -1203\n" +
                "vhu inc -464 if zeq == -192\n" +
                "ug inc 469 if txk > -1367\n" +
                "l dec 541 if px >= -1106\n" +
                "s dec 632 if s <= -2282\n" +
                "djl dec 289 if q <= -245\n" +
                "xhe dec -816 if b >= -189\n" +
                "kps dec -166 if rz != -2326\n" +
                "txk inc -207 if txk == -1362\n" +
                "o inc -566 if kq < 879\n" +
                "ug dec 662 if kyh == -1807\n" +
                "s inc -147 if vhu != -1653\n" +
                "djl dec -675 if mp >= -200\n" +
                "c inc 734 if zeq < -191\n" +
                "y dec 440 if x < -1503\n" +
                "tup dec 267 if zeq == -192\n" +
                "o dec 971 if tup == -3909\n" +
                "c dec -880 if x > -1510\n" +
                "o dec 75 if s == -2279\n" +
                "jb dec 956 if c < 5659\n" +
                "txk dec -95 if c > 5655\n" +
                "wdc inc -521 if jb > -113\n" +
                "djl dec -798 if xhe <= 1901\n" +
                "vhu inc 711 if jb <= -104\n" +
                "jb dec -44 if tup < -3906\n" +
                "kyh inc 57 if wdc != -860\n" +
                "jb dec -817 if xhe <= 1902\n" +
                "x inc 91 if rz <= -2313\n" +
                "txk dec -604 if kps != 69\n" +
                "vhu inc -714 if xhe != 1894\n" +
                "txk inc 590 if rz <= -2318\n" +
                "tup dec -557 if vhu > -1649\n" +
                "wdc inc 621 if c == 5657\n" +
                "q inc -39 if ug <= 1067\n" +
                "s inc 947 if jb > 752\n" +
                "rz inc 974 if xhe != 1902\n" +
                "kq dec 787 if tup < -3913\n" +
                "px dec 41 if djl != 399\n" +
                "q dec 618 if kyh >= -1758\n" +
                "kq inc -265 if xhe != 1906\n" +
                "kps dec 748 if zeq >= -188\n" +
                "s dec 78 if q != -908\n" +
                "c inc 10 if px >= -1160\n" +
                "j inc 526 if djl < 411\n" +
                "s dec -329 if vhu == -1653\n" +
                "l inc 922 if px < -1149\n" +
                "y dec 546 if o >= 1738\n" +
                "tup dec 505 if rz >= -1347\n" +
                "zeq dec 983 if px < -1149\n" +
                "j inc -997 if txk < -275\n" +
                "rz inc 216 if xhe >= 1890\n" +
                "jb inc -616 if vhu >= -1664\n" +
                "kps dec 357 if wdc == -234\n" +
                "jb inc 241 if zeq >= -1184\n" +
                "djl dec -907 if kps < -285\n" +
                "xhe dec 699 if y < 650\n" +
                "x inc 319 if tup <= -4415\n" +
                "l dec -715 if x != -1419\n" +
                "tup dec -13 if rz > -1133\n" +
                "vhu dec -127 if mp > -209\n" +
                "kps inc 907 if mp < -196\n" +
                "kyh dec -953 if vhu >= -1530\n" +
                "gr dec 47 if mp >= -196\n" +
                "wdc dec 462 if px != -1164\n" +
                "wdc dec 441 if zeq > -1184\n" +
                "s dec 846 if ug > 1057\n" +
                "djl inc 418 if s >= -2260\n" +
                "tup inc -428 if ug == 1065\n" +
                "djl inc -471 if ug >= 1065\n" +
                "x dec 580 if tup < -4825\n" +
                "o dec 825 if kps < 619\n" +
                "s inc -901 if tup <= -4823\n" +
                "c dec -641 if xhe >= 1200\n" +
                "j inc -966 if gr > -1205\n" +
                "px dec 141 if zeq < -1173\n" +
                "kyh inc -244 if gr < -1192\n" +
                "j inc 759 if zeq < -1166\n" +
                "xhe dec -129 if axi >= -2999\n" +
                "gr inc 567 if axi == -2990\n" +
                "x inc 856 if axi <= -2993\n" +
                "kq dec -151 if djl < 351\n" +
                "kps dec -397 if q != -907\n" +
                "axi inc -714 if jb > 370\n" +
                "y inc -630 if tup > -4834\n" +
                "o dec 610 if y <= 16\n" +
                "ug inc -172 if jb > 380\n" +
                "gr dec -531 if s == -3157\n" +
                "s dec -860 if o > 1126\n" +
                "djl inc 584 if j >= 745\n" +
                "tup inc 861 if c < 5677\n" +
                "axi inc 562 if px < -1290\n" +
                "axi inc 55 if txk > -271\n" +
                "djl inc -747 if gr < -100\n" +
                "ug inc 601 if q > -896\n" +
                "x inc -77 if b < -175\n" +
                "l dec 448 if txk > -289\n" +
                "gr inc 469 if axi <= -3135\n" +
                "jb inc -627 if q < -913\n" +
                "s dec 830 if kps > 1020\n" +
                "l dec -386 if j <= 760\n" +
                "c dec -119 if axi != -3149\n" +
                "djl inc 960 if x == -2070\n" +
                "kq dec 326 if mp != -197\n" +
                "s inc -453 if y == 17\n" +
                "gr inc 386 if j == 752\n" +
                "q inc -147 if ug == 1065\n" +
                "s dec 657 if px <= -1303\n" +
                "b dec 810 if c < 5781\n" +
                "b inc -918 if q < -1051\n" +
                "zeq inc 342 if px > -1300\n" +
                "o inc 54 if kps >= 1018\n" +
                "j inc 239 if zeq <= -827\n" +
                "ug dec 428 if y < 23\n" +
                "ug inc -433 if mp <= -203\n" +
                "jb inc -356 if txk <= -286\n" +
                "ug dec -760 if j <= 989\n" +
                "djl inc -770 if axi >= -3142\n" +
                "wdc inc -880 if wdc != -1140\n" +
                "b dec -461 if o != 1184\n" +
                "mp inc 319 if px >= -1301\n" +
                "txk dec -110 if o <= 1196\n" +
                "s dec -752 if wdc < -2007\n" +
                "vhu dec 278 if zeq <= -825\n" +
                "px dec -505 if b == -642\n" +
                "ug dec -184 if kq > 430\n" +
                "kyh dec 507 if kps <= 1024\n" +
                "l inc -115 if kq > 445\n" +
                "q inc -359 if s > -2375\n" +
                "kq dec -35 if o >= 1193\n" +
                "l dec -610 if vhu <= -1810\n" +
                "xhe dec 124 if x <= -2064\n" +
                "txk inc 167 if ug <= 828\n" +
                "y inc 684 if q > -1058\n" +
                "xhe dec 706 if c > 5777\n" +
                "vhu inc -533 if rz > -1137\n" +
                "ug inc 140 if s >= -2379\n" +
                "kyh dec -93 if l <= 2294\n" +
                "jb inc -112 if axi < -3139\n" +
                "ug dec 987 if x < -2060\n" +
                "zeq dec 828 if axi > -3146\n" +
                "jb dec -573 if ug >= -31\n" +
                "gr inc -56 if o != 1190\n" +
                "txk dec 752 if xhe > 494\n" +
                "c dec 616 if l != 2289\n" +
                "tup inc 474 if tup < -3961\n" +
                "l dec -950 if mp != 125\n" +
                "zeq inc 516 if j == 991\n" +
                "l inc 313 if x >= -2071\n" +
                "rz inc 545 if djl > 370\n" +
                "txk inc 268 if o <= 1190\n" +
                "kq dec -484 if vhu >= -2330\n" +
                "s dec 241 if kq > 442\n" +
                "xhe inc -956 if kyh != -1456\n" +
                "mp dec 315 if mp == 120\n" +
                "wdc dec 925 if vhu >= -2345\n" +
                "y dec -789 if vhu < -2336\n" +
                "tup dec 281 if j != 990\n" +
                "s dec 899 if vhu == -2340\n" +
                "c dec 308 if j < 999\n" +
                "l inc 982 if s == -3274\n" +
                "axi inc 374 if gr >= 692\n" +
                "vhu dec 586 if kyh > -1465\n" +
                "mp dec 73 if xhe <= -457\n" +
                "o inc -210 if zeq >= -1154\n" +
                "vhu inc -484 if txk == -487\n" +
                "rz inc -481 if gr >= 689\n" +
                "q inc 882 if djl < 379\n" +
                "s inc -771 if xhe > -466\n" +
                "zeq inc 804 if c < 4871\n" +
                "djl inc -899 if kq <= 441\n" +
                "axi inc -29 if ug == -26\n" +
                "jb inc -562 if txk != -495\n" +
                "j inc 921 if q <= -168\n" +
                "s inc -148 if y != 1478\n" +
                "j inc -277 if xhe >= -468\n" +
                "txk dec 210 if jb != 277\n" +
                "zeq dec -86 if vhu > -3419\n" +
                "ug inc -330 if x <= -2062\n" +
                "xhe dec -893 if c != 4859\n" +
                "gr dec -290 if j == 1635\n" +
                "zeq dec -98 if b < -647\n" +
                "kps inc 954 if ug >= -357\n" +
                "wdc dec -497 if o != 974\n" +
                "q dec -882 if o > 969\n" +
                "axi inc -65 if kq <= 446\n" +
                "wdc inc -492 if wdc > -2455\n" +
                "vhu inc 704 if gr > 987\n" +
                "x dec 672 if j > 1626\n" +
                "kyh inc -785 if jb == 268\n" +
                "ug dec 0 if djl < -514\n" +
                "jb inc -706 if txk >= -487\n" +
                "mp inc -786 if y > 1485\n" +
                "px dec -734 if j == 1635\n" +
                "jb dec 43 if c <= 4869\n" +
                "rz inc -239 if tup <= -3775\n" +
                "kyh dec -694 if djl != -531\n" +
                "l dec -520 if txk <= -483\n" +
                "q dec 499 if jb > -474\n" +
                "l dec -452 if c > 4858\n" +
                "b dec -583 if axi > -2867\n" +
                "txk inc 556 if s != -4193\n" +
                "j inc 590 if c == 4860\n" +
                "y dec -309 if q >= 204\n" +
                "xhe inc 9 if s < -4197\n" +
                "xhe inc 92 if y == 1796\n" +
                "gr dec 184 if rz == -1304\n" +
                "jb inc 213 if zeq != -251\n" +
                "xhe dec -784 if q < 217\n" +
                "q inc -117 if xhe <= 1312\n" +
                "axi dec 140 if c <= 4870\n" +
                "gr inc 915 if ug <= -351\n" +
                "px dec -135 if djl > -523\n" +
                "rz inc 475 if kyh <= -756\n" +
                "mp inc 216 if jb < -249\n" +
                "q dec 337 if wdc > -2938\n" +
                "wdc inc 288 if kyh <= -757\n" +
                "jb inc -395 if ug == -356\n" +
                "c dec 747 if rz < -827\n" +
                "jb inc 930 if px > -60\n" +
                "tup inc -581 if jb > 285\n" +
                "px inc 340 if djl < -525\n" +
                "kps inc 973 if q < -243\n" +
                "vhu dec 753 if xhe <= 1313\n" +
                "o dec -284 if s >= -4199\n" +
                "xhe dec 196 if q == -241\n" +
                "l inc 818 if x == -2742\n" +
                "txk inc 917 if rz != -837\n" +
                "kps dec 247 if ug >= -361\n" +
                "zeq inc -944 if txk >= 431\n" +
                "axi dec 700 if j <= 1643\n" +
                "x inc 958 if x >= -2751\n" +
                "axi dec 413 if tup >= -3782\n" +
                "y dec -569 if x == -1784\n" +
                "mp inc 838 if q <= -237\n" +
                "jb inc -736 if wdc < -2645\n" +
                "q inc -755 if s <= -4189\n" +
                "gr dec 489 if tup < -3774\n" +
                "s inc 185 if tup < -3783\n" +
                "y dec 708 if l != 6327\n" +
                "kyh dec 738 if zeq < -253\n" +
                "wdc dec 483 if c != 4110\n" +
                "q inc -994 if txk < 421\n" +
                "px dec 942 if tup >= -3779\n" +
                "s dec 740 if djl > -527\n" +
                "q dec 924 if y >= 1655\n" +
                "vhu dec -383 if xhe == 1114\n" +
                "b dec -285 if kyh != -1499\n" +
                "zeq inc -594 if ug < -351\n" +
                "mp dec 349 if kps < 1733\n" +
                "gr inc -329 if gr < 1416\n" +
                "ug inc -423 if l > 6323\n" +
                "s inc 681 if jb > -470\n" +
                "o inc 802 if s <= -4258\n" +
                "kyh inc 979 if kps <= 1738\n" +
                "kq dec -48 if mp < -348\n" +
                "kyh dec 602 if b <= -58\n" +
                "zeq dec -844 if wdc != -3141\n" +
                "gr inc -725 if zeq < -4\n" +
                "px dec -295 if px >= -1005\n" +
                "kq inc -756 if s >= -4259\n" +
                "jb inc 775 if tup >= -3777\n" +
                "axi inc 181 if zeq != -6\n" +
                "txk dec 397 if c <= 4123\n" +
                "gr inc 518 if s >= -4246\n" +
                "rz dec 497 if c != 4107\n" +
                "l dec -579 if wdc == -3132\n" +
                "zeq inc 56 if kq < -269\n" +
                "wdc dec -268 if l <= 6906\n" +
                "zeq dec 241 if c >= 4106\n" +
                "djl dec 866 if j < 1629\n" +
                "txk dec 546 if vhu >= -3081\n" +
                "kyh inc -667 if xhe < 1122\n" +
                "o dec 759 if o == 1262\n" +
                "vhu inc -288 if rz != -1324\n" +
                "rz inc 355 if djl < -521\n" +
                "x inc 126 if rz == -972\n" +
                "djl dec 845 if l == 6904\n" +
                "vhu inc 551 if mp == -349\n" +
                "o dec -327 if axi == -3934\n" +
                "s inc 121 if mp < -349\n" +
                "djl inc 87 if q == -1920\n" +
                "zeq dec -157 if x != -1659\n" +
                "c dec -953 if j > 1635\n" +
                "ug inc -247 if mp >= -351\n" +
                "q dec 408 if l < 6914\n" +
                "rz dec 610 if axi >= -3936\n" +
                "jb dec 90 if kq <= -270\n" +
                "o dec -899 if ug > -1027\n" +
                "c dec 985 if j <= 1638\n" +
                "vhu dec 684 if c != 3130\n" +
                "s dec -392 if axi == -3934\n" +
                "tup inc 528 if kps != 1724\n" +
                "kyh dec 200 if j != 1635\n" +
                "gr dec -288 if wdc != -2865\n" +
                "j inc 253 if b > -64\n" +
                "c inc 102 if zeq < -26\n" +
                "q dec 415 if q >= -2336\n" +
                "l dec -913 if tup >= -3251\n" +
                "c inc 678 if px <= -705\n" +
                "b inc -887 if wdc != -2864\n" +
                "gr dec -710 if axi >= -3936\n" +
                "kyh inc -871 if q != -2749\n" +
                "j inc 262 if l <= 7821\n" +
                "tup dec -906 if o <= 1736\n" +
                "j dec -191 if jb > 218\n" +
                "mp dec 296 if tup >= -2342\n" +
                "j inc 582 if ug == -1026\n" +
                "y dec -480 if c == 3910\n" +
                "tup inc 184 if mp < -635\n" +
                "rz inc 825 if tup != -2162\n" +
                "q inc 931 if tup != -2153\n" +
                "b inc 223 if kyh <= -2657\n" +
                "xhe inc -399 if jb > 216\n" +
                "vhu dec -109 if kq > -276\n" +
                "txk inc -925 if xhe != 714\n" +
                "l inc -221 if axi <= -3934\n" +
                "axi inc -226 if b != 160\n" +
                "px inc 820 if vhu <= -2702\n" +
                "rz inc 638 if mp <= -636\n" +
                "axi inc -198 if x <= -1653\n" +
                "y inc 259 if djl >= -1285\n" +
                "j inc -665 if zeq <= -41\n" +
                "axi dec 906 if y <= 2403\n" +
                "axi inc -807 if px < 117\n" +
                "zeq inc 372 if o >= 1723\n" +
                "x inc -462 if s >= -3851\n" +
                "rz inc -852 if gr < 1355\n" +
                "y inc 698 if l >= 7606\n" +
                "y inc -731 if o == 1729\n" +
                "o inc 894 if l <= 7599\n" +
                "x inc -722 if xhe <= 719\n" +
                "kps inc -502 if b <= 165\n" +
                "djl dec 847 if zeq <= 335\n" +
                "s dec -400 if l >= 7594\n" +
                "zeq dec 513 if ug >= -1021\n" +
                "y inc 797 if wdc != -2864\n" +
                "b inc 332 if b >= 158\n" +
                "gr dec -59 if wdc <= -2860\n" +
                "vhu inc -968 if zeq < 334\n" +
                "wdc dec 100 if wdc == -2864\n" +
                "s dec -862 if y <= 1672\n" +
                "axi dec -319 if o >= 2632\n" +
                "j dec -294 if kyh > -2670\n" +
                "mp inc 342 if mp > -647\n" +
                "ug inc 337 if mp < -311\n" +
                "kq dec 702 if tup > -2163\n" +
                "px inc -729 if px >= 119\n" +
                "zeq dec 161 if xhe != 711\n" +
                "jb inc -745 if kyh <= -2654\n" +
                "txk inc 671 if djl < -1280\n" +
                "y inc -974 if gr < 1425\n" +
                "vhu inc 929 if gr == 1417\n" +
                "djl inc -771 if vhu < -1765\n" +
                "y dec 764 if vhu >= -1783\n" +
                "axi inc 913 if kq >= -969\n" +
                "y inc 428 if rz != -122\n" +
                "j inc 44 if c > 3901\n" +
                "wdc inc -219 if kyh == -2660\n" +
                "rz dec -713 if wdc <= -3178\n" +
                "jb dec -510 if kyh == -2660\n" +
                "rz inc -835 if mp <= -306\n" +
                "xhe inc 461 if tup <= -2152\n" +
                "c dec -986 if ug < -1017\n" +
                "wdc dec 838 if txk != -777\n" +
                "xhe dec -402 if zeq != 186\n" +
                "x inc -791 if tup < -2155\n" +
                "o inc -453 if jb >= -12\n" +
                "ug dec -531 if s <= -2591\n" +
                "s inc -552 if b > 500\n" +
                "vhu inc -780 if tup == -2157\n" +
                "zeq inc -198 if l <= 7601\n" +
                "ug dec 875 if vhu == -2555\n" +
                "y inc -896 if j == 3261\n" +
                "o dec -9 if kq >= -973\n" +
                "ug inc 383 if c == 4896\n" +
                "y dec 592 if l < 7601\n" +
                "q dec -111 if px < 122\n" +
                "l inc -845 if s < -2593\n" +
                "tup dec -602 if zeq >= -12\n" +
                "o dec -689 if ug > -997\n" +
                "c dec -587 if ug < -984\n" +
                "c dec -376 if mp >= -305\n" +
                "px inc 159 if o > 2866\n" +
                "txk dec -763 if rz < 597\n" +
                "rz inc -403 if ug <= -983\n" +
                "px dec 393 if l != 6759\n" +
                "kq dec 65 if q >= -1707\n" +
                "q dec 363 if gr <= 1407\n" +
                "rz inc -205 if c >= 5856\n" +
                "ug inc -810 if kyh < -2653\n" +
                "px inc -11 if jb >= -19\n" +
                "tup inc -997 if mp >= -297\n" +
                "l inc 365 if kps <= 1219\n" +
                "c dec 568 if txk <= 0\n" +
                "x dec 704 if s != -2598\n" +
                "gr inc 997 if gr != 1417\n" +
                "djl dec -200 if j <= 3270\n" +
                "vhu dec 534 if jb < -8\n" +
                "px inc 726 if q == -1701\n" +
                "mp dec -952 if wdc < -4016\n" +
                "zeq dec 851 if axi < -6072\n" +
                "rz dec -92 if djl <= -1849\n" +
                "wdc dec 115 if y == -1133\n" +
                "vhu dec -641 if mp < 652\n" +
                "b inc -389 if y != -1126\n" +
                "kps dec -429 if vhu <= -2440\n" +
                "rz dec 242 if l < 6758\n" +
                "axi dec -745 if jb > -16\n" +
                "j inc 343 if gr > 1415\n" +
                "x dec -733 if ug <= -1795\n" +
                "axi dec 6 if txk != -4\n" +
                "jb dec -438 if b == 107\n" +
                "axi inc -488 if q <= -1699\n" +
                "kq inc -648 if j <= 3610\n" +
                "jb dec 270 if wdc != -4139\n" +
                "txk dec 356 if axi >= -5819\n" +
                "txk dec -620 if b <= 111\n" +
                "l dec 394 if s == -2601\n" +
                "o inc 128 if x <= -2433\n" +
                "ug dec -749 if rz > -174\n" +
                "zeq dec 374 if gr > 1410\n" +
                "kyh dec -901 if mp != 647\n" +
                "b inc -161 if tup != -2157\n" +
                "txk inc -208 if o <= 3002\n" +
                "ug dec 940 if y != -1126\n" +
                "kyh inc -956 if tup == -2157\n" +
                "xhe dec 457 if b >= 101\n" +
                "mp inc -966 if c > 5290\n" +
                "px inc -408 if djl <= -1844\n" +
                "kps dec -599 if kq > -1683\n" +
                "jb dec 798 if gr <= 1426\n" +
                "wdc inc -749 if kq < -1685\n" +
                "tup inc 254 if txk != 51\n" +
                "b dec 841 if jb < -631\n" +
                "o dec 961 if l > 6758\n" +
                "wdc dec -597 if kq <= -1677\n" +
                "px dec -414 if l < 6757\n" +
                "kps inc 373 if px < 610\n" +
                "xhe dec 887 if ug >= -1992\n" +
                "kyh inc -715 if kyh > -2712\n" +
                "l dec -919 if rz <= -164\n" +
                "xhe dec -127 if l <= 7675\n" +
                "x dec 483 if b <= -727\n" +
                "jb inc 659 if ug >= -1997\n" +
                "tup dec -278 if jb > 17\n" +
                "jb dec 295 if c >= 5299\n" +
                "kq dec 624 if q >= -1703\n" +
                "xhe dec -771 if vhu != -2455\n" +
                "c inc 809 if kyh > -2716\n" +
                "djl inc 533 if mp < -307\n" +
                "xhe inc -291 if q == -1701\n" +
                "kyh inc -677 if x > -2928\n" +
                "b inc 828 if txk <= 54\n" +
                "s dec -989 if x > -2929\n" +
                "txk dec -303 if x != -2930\n" +
                "rz dec 462 if jb < 25\n" +
                "tup dec 378 if mp < -308\n" +
                "y dec -173 if xhe == 841\n" +
                "tup inc -808 if l >= 7669\n" +
                "s inc -185 if txk >= 347\n" +
                "s dec -782 if txk < 361\n" +
                "axi inc 551 if xhe >= 834\n" +
                "kq dec -155 if tup > -2814\n" +
                "rz inc -169 if gr >= 1422\n" +
                "mp dec -938 if y == -960\n" +
                "vhu dec 779 if zeq != -394\n" +
                "rz dec -788 if zeq != -389\n" +
                "djl dec 936 if kq != -2164\n" +
                "x inc -767 if c != 6096\n" +
                "xhe inc 425 if kyh <= -3394\n" +
                "kq dec 653 if rz == 162\n" +
                "ug dec -256 if kyh >= -3399\n" +
                "djl dec 160 if kyh == -3392\n" +
                "tup inc -748 if q != -1711\n" +
                "kq dec -338 if axi < -5254\n" +
                "o inc 223 if ug < -1723\n" +
                "c dec -66 if q < -1699\n" +
                "gr inc -340 if tup <= -3550\n" +
                "txk inc 471 if s == -1012\n" +
                "wdc dec 105 if y != -951\n" +
                "gr dec -866 if tup < -3566\n" +
                "kps dec 5 if wdc <= -4392\n" +
                "wdc dec -614 if wdc != -4390\n" +
                "l inc 511 if tup <= -3555\n" +
                "y dec 479 if ug != -1732\n" +
                "txk dec 712 if y != -960\n" +
                "b dec -508 if wdc < -3776\n" +
                "ug dec -573 if b != 609\n" +
                "vhu inc -954 if kps > 2022\n" +
                "gr dec -964 if djl >= -2412\n" +
                "txk inc 533 if px != 595\n" +
                "x inc 538 if gr < 1084\n" +
                "j inc -31 if b != 602\n" +
                "rz inc -934 if b != 600\n" +
                "j dec 566 if wdc != -3788\n" +
                "l dec 756 if l != 8189\n" +
                "q inc -567 if zeq < -384\n" +
                "rz inc -745 if wdc > -3788\n" +
                "px dec 453 if tup > -3558\n" +
                "ug inc -665 if l >= 7425\n" +
                "tup inc 798 if ug != -1816\n" +
                "ug inc 709 if jb == 23\n" +
                "txk dec -173 if ug > -1822\n" +
                "ug inc 501 if xhe < 850\n" +
                "gr inc 90 if o != 3219\n" +
                "tup inc -919 if kq <= -2461\n" +
                "axi dec -83 if xhe == 841\n" +
                "y dec 200 if wdc >= -3779\n" +
                "wdc dec 424 if o != 3226\n" +
                "zeq dec 253 if kq > -2473\n" +
                "rz dec -281 if mp <= 629\n" +
                "tup inc 760 if kps != 2031\n" +
                "mp dec -248 if y >= -1161\n" +
                "s dec 902 if djl <= -2408\n" +
                "mp dec -498 if txk < 1359\n" +
                "mp inc 996 if kyh == -3394\n" +
                "jb inc 492 if px <= 604\n" +
                "px dec 701 if mp >= 863\n" +
                "s dec -957 if tup < -2912\n" +
                "gr inc 214 if tup == -2920\n" +
                "o inc -730 if jb > 508\n" +
                "rz dec -646 if jb <= 513\n" +
                "x dec -2 if x == -3150\n" +
                "b inc -938 if l != 7420\n" +
                "x inc -268 if j != 3048\n" +
                "kq inc -589 if ug > -1317\n" +
                "xhe inc 83 if kps <= 2027\n" +
                "mp dec -962 if kyh <= -3383\n" +
                "j inc 977 if djl == -2415\n" +
                "axi dec 169 if mp <= 1831\n" +
                "px dec -774 if px != -92\n" +
                "kps dec 279 if px < 678\n" +
                "j inc -731 if o <= 2489\n" +
                "x inc -58 if c > 6167\n" +
                "gr inc 610 if vhu <= -3398\n" +
                "vhu inc 209 if kq > -2467\n" +
                "txk inc 11 if jb == 511\n" +
                "rz dec 341 if zeq >= -639\n" +
                "djl dec 297 if q > -2263\n" +
                "xhe dec -357 if o < 2496\n" +
                "kyh dec -579 if b >= -330\n" +
                "kps inc 186 if l < 7426\n" +
                "kyh dec -25 if y == -1160\n" +
                "djl inc 996 if djl >= -2416\n" +
                "mp inc 773 if zeq < -637\n" +
                "xhe inc 759 if tup > -2929\n" +
                "s inc -122 if axi == -5349\n" +
                "vhu dec -603 if l == 7425\n" +
                "gr dec 741 if tup <= -2919\n" +
                "djl dec -996 if mp < 2608\n" +
                "kps dec -645 if vhu < -2793\n" +
                "px dec -25 if djl < -416\n" +
                "px dec -624 if djl > -429\n" +
                "px dec -380 if kq > -2480\n" +
                "mp dec -526 if xhe != 2031\n" +
                "q dec -358 if zeq <= -639\n" +
                "rz inc 218 if jb > 507\n" +
                "q inc -54 if x >= -3420\n" +
                "mp dec 140 if kps < 2581\n" +
                "vhu inc 234 if vhu == -2799\n" +
                "q dec -428 if b == -336\n" +
                "mp dec 825 if wdc > -4209\n" +
                "px dec 22 if x != -3416\n" +
                "j dec -952 if axi > -5352\n" +
                "y inc -551 if y > -1167\n" +
                "djl inc -832 if txk >= 1364\n" +
                "vhu inc -318 if px >= 1701\n" +
                "o dec 783 if txk <= 1376\n" +
                "o inc -479 if txk >= 1365\n" +
                "j dec -924 if px > 1700\n" +
                "ug dec -230 if ug == -1323\n" +
                "q inc -572 if o > 1231\n" +
                "xhe inc 266 if ug <= -1099\n" +
                "y inc 810 if xhe >= 2035\n" +
                "j inc 599 if tup == -2920\n" +
                "xhe dec -795 if o <= 1232\n" +
                "b inc -972 if vhu <= -2884\n" +
                "q dec 715 if s != -1079\n" +
                "vhu inc -143 if y <= -902\n" +
                "wdc inc -704 if kq >= -2477\n" +
                "wdc inc 718 if rz >= -372\n" +
                "jb dec -224 if vhu != -2889\n" +
                "kps inc -197 if ug <= -1092\n" +
                "x inc -81 if x == -3416\n" +
                "xhe dec 492 if tup < -2917\n" +
                "px inc -853 if kq != -2470\n" +
                "djl inc 881 if kq < -2462\n" +
                "q inc 306 if j < 5768\n" +
                "mp inc 618 if b <= -332\n" +
                "s inc 651 if vhu >= -2889\n" +
                "x dec 852 if ug == -1092\n" +
                "x inc -61 if ug >= -1088\n" +
                "tup inc -674 if mp > 2775\n" +
                "j dec 656 if y < -892\n" +
                "kps inc 314 if djl < -364\n" +
                "txk inc -344 if x >= -3498\n" +
                "c dec -768 if ug >= -1101\n" +
                "mp dec -693 if axi != -5349\n" +
                "zeq inc 495 if axi <= -5347\n" +
                "b inc -944 if b <= -330\n" +
                "x inc 790 if mp == 2783\n" +
                "djl inc -776 if y > -911\n" +
                "txk inc 156 if wdc == -4189\n" +
                "x dec 929 if c < 6942\n" +
                "q dec -922 if j >= 5103\n" +
                "x dec 254 if ug <= -1089\n" +
                "mp inc 781 if px >= 1697\n" +
                "kps dec 639 if djl != -1141\n" +
                "j dec -543 if rz == -372\n" +
                "jb dec -961 if y <= -892\n" +
                "djl inc 430 if o == 1237\n" +
                "q dec -64 if o == 1227\n" +
                "txk dec -438 if px >= 1702\n" +
                "wdc inc -117 if x > -3894\n" +
                "px dec -660 if b >= -1287\n" +
                "tup inc 18 if o <= 1230\n" +
                "jb inc 27 if vhu != -2883\n" +
                "vhu inc 554 if x != -3890\n" +
                "s inc -344 if xhe == 2343\n" +
                "px dec 208 if axi < -5347\n" +
                "px inc -859 if o <= 1233\n" +
                "rz inc 717 if q > -242\n" +
                "x dec 315 if y != -910\n" +
                "vhu inc 846 if s == -779\n" +
                "zeq dec -557 if x == -4205\n" +
                "xhe inc -897 if txk < 1621\n" +
                "y inc -130 if kq <= -2464\n" +
                "c dec 430 if djl == -1150\n" +
                "b dec -73 if kyh < -3363\n" +
                "o inc -337 if kyh == -3367\n" +
                "xhe inc 924 if l < 7417\n" +
                "tup dec -126 if x == -4205\n" +
                "j dec -967 if kps == 2054\n" +
                "zeq inc -908 if mp == 3559\n" +
                "j inc -501 if txk != 1619\n" +
                "djl inc -810 if y >= -1033\n" +
                "y dec 525 if djl <= -1960\n" +
                "l inc 389 if x <= -4199\n" +
                "gr inc 111 if jb > 1692\n" +
                "x inc -206 if b > -1200\n" +
                "o inc -928 if s <= -765\n" +
                "l dec -372 if txk > 1612\n" +
                "jb inc -937 if kq == -2470\n" +
                "kq dec -296 if gr < 1276\n" +
                "j dec -643 if rz > -378\n" +
                "kps dec 412 if zeq >= 399\n" +
                "o inc -224 if kq < -2172\n" +
                "y inc -572 if jb == 759\n" +
                "l inc -663 if j < 6760\n" +
                "xhe inc -284 if l <= 7531\n" +
                "kps dec 209 if y <= -2119\n" +
                "tup inc 216 if mp != 3574\n" +
                "px dec -421 if ug >= -1101\n" +
                "wdc inc 854 if ug != -1095\n" +
                "djl inc -860 if wdc > -3453\n" +
                "j dec -541 if ug < -1087\n" +
                "b dec -27 if q != -234\n" +
                "kyh inc -345 if y == -2128\n" +
                "o inc -84 if xhe > 1163\n" +
                "b inc -512 if s > -782\n" +
                "ug dec -772 if l >= 7516\n" +
                "wdc inc -718 if wdc >= -3453\n" +
                "tup inc 48 if rz == -375\n" +
                "c inc 372 if kyh <= -3703\n" +
                "tup dec 753 if b <= -1683\n" +
                "l inc -573 if kps <= 1434\n" +
                "kq inc -589 if axi == -5349\n" +
                "q dec -905 if x >= -4213\n" +
                "jb inc 716 if y >= -2137\n" +
                "q dec -864 if gr <= 1278\n" +
                "axi dec -402 if ug != -322\n" +
                "px dec -442 if j <= 7300");

        register.runInstructions();

        System.out.println(register.valueOfHighestRegister());
        System.out.println(register.getHighestNumberEver());
    }

}
