package Day_7_2017;

public class RecursiveCircus {
    int[] weigths;
    String[][] nodes;
    int[][] nodeNumbers;

    RecursiveCircus(String input){
        input =  input.replaceAll("\\(","");
        input =  input.replaceAll("\\)","");
        input =  input.replaceAll("-> ","");
        input =  input.replaceAll(",","");
        String[] lines = input.split("\n");
        weigths = new int[lines.length];
        nodes = new String [lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");

            nodes[i] = new String[line.length-1];

            nodes[i][0] = line[0];

            weigths[i] = Integer.parseInt(line[1]);

            for (int j = 2; j < line.length; j++) {
                nodes[i][j-1] = line[j];
            }
        }

        nodeNumbers = new int[nodes.length][];
        for (int i = 0; i < nodes.length; i++) {
            nodeNumbers[i] = new int[nodes[i].length];
            for (int j = 0; j < nodes[i].length; j++) {
                nodeNumbers[i][j] = findNode(nodes[i][j]);
            }
        }
    }

    int[] nodelevels(){
        int[] levels = new int[nodes.length];

        for (int i = 0; i < nodeNumbers.length; i++) {
            for (int j = 1; j < nodeNumbers[i].length; j++) {
                levels[nodeNumbers[i][j]]++;
            }
        }

        return levels;
    }

    void printBottomNodes(){
        int[] levels = nodelevels();

        for (int i = 0; i < levels.length; i++) {
            if (levels[i]==0){
                System.out.printf("%s is a bottom node, it has index %d\n",nameNode(i),i);
            }
        }
    }

    int bottomNode(){
        int[] levels = nodelevels();

        for (int i = 0; i < levels.length; i++) {
            if (levels[i]==0){
                return i;
            }
        }
        return -1;
    }

    int totalWeightOfNode(int index){
        int weight = weigths[index];

        for (int i = 1; i < nodeNumbers[index].length; i++) {
            weight += totalWeightOfNode(nodeNumbers[index][i]);
        }
        return weight;
    }

    int individualNodeWeight(int index){
        return weigths[index];
    }


    boolean isBalanced(int index){

        if (nodeNumbers[index].length > 2) {
            int weight = totalWeightOfNode(nodeNumbers[index][1]);
            for (int i = 2; i < nodeNumbers[index].length; i++) {
                if (totalWeightOfNode(nodeNumbers[index][i]) != weight) {
                    return false;
                }
            }
        }
        return true;
    }

    private int firstUnbalancedNode(int start){

        for (int i = 1; i < nodeNumbers[start].length; i++) {
            if (!isBalanced(nodeNumbers[start][i])){
                return firstUnbalancedNode(nodeNumbers[start][i]);
            }
        }

        return start;
    }

    String nameNode(int index){
        return nodes[index][0];
    }

    int findNode(String name){
        for (int i = 0; i < nodes.length; i++) {
            if (nameNode(i).equals(name)){
                return i;
            }
        }
        return -1;
    }


    static int indexOfOutlier(int... candidates){
        int number1 = candidates[0];
        int indexOf2 = -1;


        for (int i = 0; i < candidates.length; i++) {
            if (indexOf2==-1){
                if (number1!=candidates[i]){
                    indexOf2 = i;
                }
            }else {
                if (number1==candidates[i]){
                    return indexOf2;
                } else {
                    return 0;
                }
            }
        }

        return -1;
    }

    void solution2(){

        int firstUnbalancedNode = firstUnbalancedNode(bottomNode());

        System.out.printf("The first unbalanced node is %s at index %d\n",nameNode(firstUnbalancedNode), firstUnbalancedNode);
        System.out.printf("It has a personal weigth of %d and a total weigth of %d\n",weigths[firstUnbalancedNode],totalWeightOfNode(firstUnbalancedNode));

        System.out.printf("Its children are:\n");

        int[] totalWeights = new int[nodeNumbers[firstUnbalancedNode].length-1];

        for (int i = 1; i < nodeNumbers[firstUnbalancedNode].length; i++) {
            int childNode = nodeNumbers[firstUnbalancedNode][i];
            System.out.printf("%-10s At index %3d, with a total weigth of %d and a personal weigth of %d\n",nameNode(childNode)+":", childNode,
                    totalWeightOfNode(childNode), weigths[childNode]);
            totalWeights[i-1]=totalWeightOfNode(nodeNumbers[firstUnbalancedNode][i]);
        }

        int unbalanceIndex = indexOfOutlier(totalWeights)+1;
        int unbalancingNode = nodeNumbers[firstUnbalancedNode][unbalanceIndex];

        int idealTotalWeight = 0;
        for (int i = 1; i < nodeNumbers[firstUnbalancedNode].length; i++) {
            if (i!=unbalanceIndex){
                idealTotalWeight=totalWeightOfNode(nodeNumbers[firstUnbalancedNode][i]);
                break;
            }
        }

        int difference = idealTotalWeight - totalWeightOfNode(unbalancingNode);
        int idealPersonalWeight = weigths[unbalancingNode]+difference;

        System.out.printf("%s on index %d, should have its personal weight, %d, changed by %d to %d\n",
                nameNode(unbalancingNode),unbalancingNode,weigths[unbalancingNode], difference,idealPersonalWeight);


    }

    public static void main(String[] args) {
        RecursiveCircus weight = new RecursiveCircus("suvtxzq (242) -> tdoxrnb, oanxgk\n" +
                "smjsfux (7)\n" +
                "oanxgk (68)\n" +
                "tvvfszm (66)\n" +
                "xprwsaf (266) -> uynuje, hhxlqcl\n" +
                "imkbcte (256) -> aeoojsr, muxff\n" +
                "uyxvzt (1046) -> uemmhd, ctdjc, gxqkboz, nvudb, dadueoc\n" +
                "ancik (61)\n" +
                "oysettw (152) -> fjojctd, eiiis, eilacwy\n" +
                "chfkzye (88)\n" +
                "maytl (149) -> uprsewo, obkvvy\n" +
                "qdyxf (95)\n" +
                "ssqhzgo (183) -> idwscr, jwmobb\n" +
                "hmtesft (62)\n" +
                "prayxh (377) -> bybrtqt, ftlxelc\n" +
                "rxlcg (16) -> soscup, gvqhsi\n" +
                "vhrxv (76)\n" +
                "bpmgs (81)\n" +
                "tngrxak (82)\n" +
                "odlmlp (19)\n" +
                "uocgh (78)\n" +
                "qdiuck (203) -> opxgy, evfob\n" +
                "hmpgq (86)\n" +
                "agrorp (98)\n" +
                "fnubw (7991) -> uyxvzt, xzzbz, zqbno, fxftvvi\n" +
                "pjdwe (71)\n" +
                "agmcum (9)\n" +
                "plxbhxv (91) -> pburz, crbuu, ancik, oglzi\n" +
                "tjvdz (68)\n" +
                "fwjrt (93)\n" +
                "smubum (79)\n" +
                "yojgof (34)\n" +
                "obkvvy (46)\n" +
                "lbhigx (32)\n" +
                "yixnd (10)\n" +
                "uysrm (255) -> vjzzaxi, mnegwoy\n" +
                "ggfpru (99)\n" +
                "pffjr (75)\n" +
                "bzoui (45) -> qzzuio, dzkcq\n" +
                "vrpls (22)\n" +
                "umrlyk (196) -> eygeo, xmhogfl\n" +
                "nkjocfh (81)\n" +
                "dzkcq (24)\n" +
                "ffpmvco (95)\n" +
                "fdlcwg (78)\n" +
                "gbvlb (214) -> sdjtpgj, ijhxyp\n" +
                "idhmz (17)\n" +
                "jouimd (55)\n" +
                "jnetreq (185)\n" +
                "xejdjnu (87)\n" +
                "gyeakaj (84)\n" +
                "gcdvju (22)\n" +
                "dadueoc (54) -> ywbagad, amkkd\n" +
                "ebpdat (63)\n" +
                "jcyop (522) -> qzegoz, rjbsi, fbbhkn, fafmv, pgiscip, fvzlw, etyllx\n" +
                "ozyiajn (137) -> iotjdfw, ulqoxr\n" +
                "pomtfpc (46)\n" +
                "baukd (52)\n" +
                "xgrkt (77)\n" +
                "mvbiu (37)\n" +
                "xhgxlcb (31)\n" +
                "xwmmdbs (48)\n" +
                "kxrwmdo (35)\n" +
                "kmmaqz (93)\n" +
                "vuoipf (1917) -> hgzal, evjtjkw, qstxfxv, yqzlm, agwseuk\n" +
                "zaoep (284) -> ffsrpnc, cvddep, mqlmpi\n" +
                "jnkjy (150) -> tdfetjs, gcpjvg, ztfmse\n" +
                "xjjge (142) -> yeavyk, cxfsjtx\n" +
                "xxcmska (51)\n" +
                "vjqrn (95) -> pnfmax, uhdvcqk, tetvxtx, ypjre\n" +
                "ffanqao (86)\n" +
                "evcrg (88)\n" +
                "htzab (142) -> douazak, wrqgf\n" +
                "xnvjeq (71)\n" +
                "oamdc (98)\n" +
                "goxkdq (343) -> vxbul, yfapady\n" +
                "evjtjkw (61) -> linzo, fzrps\n" +
                "ctmsdh (80)\n" +
                "apijq (57) -> ggbavjl, kvyvm, yfjodsp, dtiqmg, ehqjjsl, eggrxn, eaxgw\n" +
                "ctdjc (156)\n" +
                "jfagd (35)\n" +
                "ciwaqy (49)\n" +
                "fcydda (279)\n" +
                "nzkxl (10) -> wruuxfs, zviebo, fdxmq\n" +
                "kwlrqf (46) -> ngzhx, jnmwff, yacsqd, zbredd, gsabtz, gaodhdn, daziob\n" +
                "tgctm (2340) -> gtolwfw, zrjkvhs, jvrfyh\n" +
                "weasj (213) -> gzzmpmt, btmauhd, dvdfe, wqswycq\n" +
                "nbpifq (19) -> iujht, evacloi, geevgr\n" +
                "sjjfw (250) -> jpbod, xxcmska, hqkqg\n" +
                "mcltn (23)\n" +
                "tjqces (74)\n" +
                "epxsima (142) -> rpcpu, uyqja\n" +
                "uprsewo (46)\n" +
                "ikufikz (18)\n" +
                "oxtcw (60)\n" +
                "ftjsi (52)\n" +
                "cksrupl (216) -> bcqrh, bqcpk, smjsfux\n" +
                "iwrxh (793) -> wkrunp, ciwaqy\n" +
                "jgemyt (395) -> spzcts, zxdwxi\n" +
                "sdjtpgj (27)\n" +
                "tkbvq (229) -> vtvrufl, vmask, qzdgae, ltxdrag\n" +
                "fmxgjyi (67)\n" +
                "doioysi (37)\n" +
                "kvyvm (231) -> cfofvmk, mrfxp, aiifb\n" +
                "ahxab (11)\n" +
                "csjgde (313) -> ixqosm, ziclyc\n" +
                "trkehb (234) -> qhivkhk, odnxu\n" +
                "gaepxdo (10) -> dssmkge, dcfod, mnzrs\n" +
                "xneou (37) -> qdmwhp, ykuovbf, nnrup, kmmaqz\n" +
                "wcsvv (125) -> xlcwwh, wkpcmj\n" +
                "ynabij (365) -> guenoww, hyupti\n" +
                "vyksxor (74)\n" +
                "jajnukt (141) -> jzsuszj, kypvxgj, prpur, bbvcyqt\n" +
                "qvvfdz (235) -> mzxzvj, ayhtq\n" +
                "qwuwet (44)\n" +
                "wsnnpt (93)\n" +
                "kchxaz (50)\n" +
                "excue (94) -> vbklmbl, cvebrdn, tsejpo, auvkftn, odgaq, mikabr, plafq\n" +
                "inpzr (283) -> bttuf, mtpjiqv\n" +
                "ceahs (149) -> iywmq, ednoc, opoav\n" +
                "oxokip (59)\n" +
                "bjhbaeq (15) -> jbeccla, yghmpm\n" +
                "fafmv (164) -> pmcxf, sgcszn\n" +
                "kfdlwmd (76)\n" +
                "wezrbzw (90)\n" +
                "mjfmma (80)\n" +
                "unwxho (93) -> ycbensb, dqqvw, xggqps, wopctnz\n" +
                "xddneql (98)\n" +
                "ugaao (293) -> igxnbx, iuwfcfn\n" +
                "idwscr (27)\n" +
                "hkifor (56)\n" +
                "wvamomg (66)\n" +
                "fzrps (64)\n" +
                "oqatq (34)\n" +
                "cjlll (25)\n" +
                "fusrhst (85)\n" +
                "lglzu (65) -> tgoxupv, aqcvcmu\n" +
                "jicds (19)\n" +
                "dlorovb (88)\n" +
                "tjpyzok (48)\n" +
                "ewetx (18) -> aivthrm, dehctx, xejdjnu\n" +
                "ohjpwlf (89)\n" +
                "ayrmt (6)\n" +
                "drfiyc (208) -> ajvtod, mbsom, svgsp\n" +
                "llbrfnj (35)\n" +
                "ojvela (47) -> pfdhuej, eftexh, dlorovb, ihxxanb\n" +
                "mzdorez (75)\n" +
                "feecvjl (68)\n" +
                "bhjgta (936) -> jtemqlc, ztqfg, zaoep, knfjqwi\n" +
                "wrqgf (50)\n" +
                "cchsvcu (2393) -> vbahlkz, nsaunto, iidrfg\n" +
                "ehqjjsl (225) -> iiihrc, cmlrqjx\n" +
                "uwqgz (59) -> juadxcz, doulax\n" +
                "hlbyzqi (98)\n" +
                "dqwfuzn (785) -> utecy, smpbfj\n" +
                "sabrmjw (539) -> ynabij, yorve, tdfla, xneou, urxki, caaul\n" +
                "szvvmv (59)\n" +
                "nwvfcq (8)\n" +
                "olnfb (95)\n" +
                "gcgai (217) -> hmgnq, doioysi\n" +
                "rzyceeb (75) -> cpvmv, vyqeu\n" +
                "eaxgw (359) -> sumkuu, qyxyyfe\n" +
                "xezfgjm (386) -> lfmshq, btunou, fnkvv, jcetf\n" +
                "xgwbi (34)\n" +
                "hhxlqcl (69)\n" +
                "jjutz (85) -> ppmtmra, lrhov, gahmpi\n" +
                "aeede (82)\n" +
                "vhpqug (48)\n" +
                "gwcznw (78)\n" +
                "iiihrc (84)\n" +
                "ponhl (56)\n" +
                "xkljy (165) -> qeaafc, luobrd\n" +
                "sgdqadb (365) -> gzbpbzh, semuybr\n" +
                "vlstbi (265)\n" +
                "doulax (35)\n" +
                "awgit (71)\n" +
                "ukgraro (27)\n" +
                "itqxypr (40)\n" +
                "tqjnarz (86) -> ulgusti, tngrxak, jpchi\n" +
                "cjcnjv (22)\n" +
                "upalmon (65)\n" +
                "mybgqcz (34)\n" +
                "ensbsjj (10194) -> zjdlixd, enpvalm, bhjgta\n" +
                "jbexqrg (43)\n" +
                "odvadyf (28)\n" +
                "yhkvwfh (14) -> efnhbg, lfgtisg\n" +
                "aeuhflj (20)\n" +
                "lxugv (93)\n" +
                "qxubf (223) -> woauuj, etlyn\n" +
                "woauuj (72)\n" +
                "lhldwh (35)\n" +
                "ehxoyp (6)\n" +
                "svhudk (55)\n" +
                "jtlqjwb (90)\n" +
                "bhgjfjp (43)\n" +
                "wojfh (76)\n" +
                "cxhplek (28)\n" +
                "eiiis (60)\n" +
                "lfmshq (86) -> jgjis, tezrxeh, kfdlwmd\n" +
                "qhoznbt (52)\n" +
                "ifcvuo (305) -> dyimr, zjybiy\n" +
                "zelat (1852) -> dtovks, jkjkxjo, etihx, xwukld\n" +
                "ptkhfgv (76)\n" +
                "yfwlsm (40) -> diodh, wqwuh, xddneql, oamdc\n" +
                "udnuwf (44)\n" +
                "topay (77)\n" +
                "rpqtlka (10)\n" +
                "uclzd (258)\n" +
                "uqcsmi (138) -> orfvwfh, mvvdw, wxqcjau\n" +
                "jkjkxjo (97)\n" +
                "sjizhzd (385)\n" +
                "awhtxfi (67)\n" +
                "mfkyyn (22)\n" +
                "khxovd (70) -> pnednw, oxtcw, zculjs\n" +
                "btuuf (36)\n" +
                "tbmtw (7374) -> jcyop, gnpvu, qokpqcj, dpulvm\n" +
                "dyimr (47)\n" +
                "ktiqs (60)\n" +
                "uqrmg (38) -> xgwbi, mybgqcz, yojgof, qssip\n" +
                "kypvxgj (37)\n" +
                "ymmmd (119) -> posqxf, xnvjeq, rmuwew\n" +
                "mazuw (20)\n" +
                "ahxil (180) -> ayrmt, ehxoyp\n" +
                "ytjvi (30)\n" +
                "gfrjfp (10456) -> vglmk, ncewylf, ofqyppk, lrrznr, krvxpc\n" +
                "sumkuu (17)\n" +
                "sogmnn (1837) -> hmpgq, ffanqao\n" +
                "ulqoxr (77)\n" +
                "dnzvald (12)\n" +
                "fhsmv (87)\n" +
                "cgoclfi (44)\n" +
                "qeaafc (36)\n" +
                "ovsdyv (241)\n" +
                "hrykmkz (59)\n" +
                "dpqsx (224) -> topay, ioinp\n" +
                "ihrnca (85)\n" +
                "bpndvbp (22)\n" +
                "fbtzaic (11651) -> nzkxl, mdbtyw, dqwfuzn\n" +
                "smpbfj (64)\n" +
                "krnlmf (22)\n" +
                "mqlmpi (12)\n" +
                "cmlrqjx (84)\n" +
                "yorve (285) -> yhwiyr, hmtesft\n" +
                "katupgz (23)\n" +
                "prpur (37)\n" +
                "iidrfg (133)\n" +
                "iofnddc (65)\n" +
                "houiwqu (64)\n" +
                "exzvkxu (26)\n" +
                "topcav (93) -> zmuxeo, glmtq\n" +
                "uoowx (48)\n" +
                "xkvgp (17)\n" +
                "paplmio (91)\n" +
                "igxnbx (46)\n" +
                "ftbzlvu (93)\n" +
                "bucdl (107)\n" +
                "bydph (90) -> bhyawz, etgphv\n" +
                "fqoary (30) -> ldcqple, gfrjfp, uvfrdrm, flnwg, dzihru, kyjva, ncuauw\n" +
                "soiwb (22)\n" +
                "iqgctux (839) -> vjqrn, hfbyx, kfibzch, fctrumt, oraqucr, ewetx, fpmlwfh\n" +
                "zxdwxi (39)\n" +
                "gzzmpmt (43)\n" +
                "pujxta (22)\n" +
                "cvebrdn (192) -> hyblgwq, dafsjnc\n" +
                "tpdpnu (63)\n" +
                "glmtq (74)\n" +
                "fdxmq (28) -> lisgic, mpsbosw, hykydhr\n" +
                "kloyc (30)\n" +
                "tfrrlt (27)\n" +
                "lwbtd (39)\n" +
                "xykzz (93)\n" +
                "ppoiun (196) -> glton, rscybf\n" +
                "gimfzpl (98)\n" +
                "adjeeo (66) -> svlzb, grluwcw, bqytirn, lzovhs\n" +
                "mbsom (14)\n" +
                "dvcdvss (76)\n" +
                "ypjre (46)\n" +
                "micrqvl (15)\n" +
                "ezzme (89)\n" +
                "dnkuuf (237) -> fitxh, iofnddc\n" +
                "rjbsi (176)\n" +
                "qdhlpqn (54) -> jrnzibg, urdzsm, pxzxws, eounhdc, goxkdq\n" +
                "zatst (36) -> udjinl, hwssapq\n" +
                "ismlzl (8)\n" +
                "uyqja (34)\n" +
                "zxuzl (808) -> lvheqh, syjwi, usqget, gowyxis\n" +
                "pbwqe (166) -> dbwnqzs, swzwgb, eeufykm\n" +
                "bzpuxrk (52)\n" +
                "qzzuio (24)\n" +
                "dtpnpq (104) -> lowahta, obidwky\n" +
                "lhumvsh (23)\n" +
                "ntintc (19)\n" +
                "bvytf (157) -> unjnjhh, xuhgu, aeede\n" +
                "gitpug (56)\n" +
                "vtvrufl (9)\n" +
                "duwrtfc (16)\n" +
                "uahwv (40)\n" +
                "fjojctd (60)\n" +
                "pburz (61)\n" +
                "btunou (210) -> qhoznbt, ftjsi\n" +
                "ieacu (179) -> fetmzid, cnzqk\n" +
                "gwzoe (72)\n" +
                "eplazoz (77)\n" +
                "uxppyj (83)\n" +
                "dvdfe (43)\n" +
                "dkorpg (39)\n" +
                "ipgte (85)\n" +
                "spzcts (39)\n" +
                "lwxoin (169) -> yvbxww, fmgdkun\n" +
                "iatnsa (34)\n" +
                "daziob (265) -> pikkduj, qjxczyx\n" +
                "knvtk (26)\n" +
                "qjxczyx (78)\n" +
                "gtolwfw (96) -> mbfhki, uftzqia\n" +
                "ussukc (8)\n" +
                "fsaitmn (425) -> ckeqik, lmqhg, zfkliql, ahhtyrn\n" +
                "dczlybu (17)\n" +
                "fcqyuld (69)\n" +
                "ibrkwsx (81)\n" +
                "mroyq (17)\n" +
                "bboim (1467) -> bydph, ppzdpud, lfmuqxa\n" +
                "dochk (175) -> ichsx, xyorm, oxhcne\n" +
                "grluwcw (78)\n" +
                "vkzmdxf (23) -> lwxoin, qdiuck, qjqmpw, kfjckfy\n" +
                "gmcrj (69) -> polkn, gejdtfw, cfuudsk, fqoary\n" +
                "lisgic (91)\n" +
                "nhkfwk (48)\n" +
                "eeufykm (25)\n" +
                "ytvur (98)\n" +
                "pikkduj (78)\n" +
                "hkstlva (19) -> stzau, zpaybpo, ibrkwsx\n" +
                "xmhogfl (31)\n" +
                "wqimeek (93)\n" +
                "eycqvhz (95)\n" +
                "xzufzqj (35)\n" +
                "iohhnkq (78)\n" +
                "ftlxelc (13)\n" +
                "jfknc (97) -> sjizhzd, efhdsgs, weasj, hrxzek, ugaao, sgdqadb, xxljznv\n" +
                "fmgdkun (24)\n" +
                "vwjspf (7)\n" +
                "vvyffam (13) -> nwecq, buodib, ypujb, ndjueqb\n" +
                "dqjepow (1158) -> waikg, ljyobyk, xbnecm, pssff, cqoxq, uokpbvj\n" +
                "piyyydu (250) -> lodjokj, oakblco, rlgru, aawuak\n" +
                "uxfcrt (240) -> ahxab, pbsabz\n" +
                "lmteo (50) -> coleqoq, ohseo\n" +
                "jprrnd (76)\n" +
                "juadxcz (35)\n" +
                "bubuic (62)\n" +
                "cqzhj (23)\n" +
                "nvudb (62) -> uebysc, cjxuwe\n" +
                "rantzzr (7)\n" +
                "tyddsz (261)\n" +
                "ixqosm (75)\n" +
                "dtrdb (264) -> bhgjfjp, jbexqrg\n" +
                "aqljjk (60)\n" +
                "gcpjvg (35)\n" +
                "xdrpbh (75)\n" +
                "yfapady (24)\n" +
                "gwlskzx (445) -> tqcdt, agmcum\n" +
                "xxljznv (29) -> ezzme, xvxezl, zjzldd, rfigs\n" +
                "ztqfg (71) -> xdfwfmo, lugxki, uxppyj\n" +
                "fnkvv (152) -> dfdawtk, mxpnyo\n" +
                "aeoojsr (15)\n" +
                "uymdisn (31)\n" +
                "jnkqo (49) -> tvcul, stfsit\n" +
                "otsbf (98)\n" +
                "lwgppcz (67)\n" +
                "ecuhckx (806) -> ddnfy, tzseb, mbxffzk\n" +
                "hqkqg (51)\n" +
                "nsaunto (13) -> uahwv, fveuo, bihjuos\n" +
                "mdbtyw (396) -> zqcrxm, lfbocy, uqrmg\n" +
                "bnlxpgs (41)\n" +
                "dafsjnc (53)\n" +
                "ofeqif (87)\n" +
                "plafq (112) -> wqimeek, fgeakc\n" +
                "oazne (238) -> pddmke, cugsuk, vyvwpp\n" +
                "unjnjhh (82)\n" +
                "hlxglt (97)\n" +
                "oglzi (61)\n" +
                "rmzdg (90) -> gfxra, oqxprqa, jfknc, mwmyxd, iqgctux, cchsvcu\n" +
                "aszlk (6)\n" +
                "xkzympi (80)\n" +
                "fcajmx (79)\n" +
                "iygrvcq (84)\n" +
                "xvxezl (89)\n" +
                "wqswycq (43)\n" +
                "wkpcmj (30)\n" +
                "gydhlh (67) -> igeuou, ggfpru\n" +
                "yhjbr (15)\n" +
                "blmrnzv (75)\n" +
                "guenoww (22)\n" +
                "ibygguq (75)\n" +
                "uaouhj (50)\n" +
                "kmvtp (80) -> ztcoo, evcrg, seqpyc, awkci\n" +
                "pnptpzb (18) -> qvvfdz, fgbkh, jnkjy, uprknk, uqcsmi, mwixu\n" +
                "rxeesk (97)\n" +
                "puiuyvv (64) -> hydsxwk, tjqces, sfkznca\n" +
                "oxtuf (84)\n" +
                "rhomwvz (72)\n" +
                "zoeyj (230) -> rznjgnj, ztocqs, itqxypr\n" +
                "xdfwfmo (83)\n" +
                "mzxzvj (10)\n" +
                "uyauqa (476) -> gbvlb, inbnp, nbpifq, kxbgcn\n" +
                "eggrxn (13) -> bfuell, oqkcc, qpshatb, ffpmvco\n" +
                "bcqrh (7)\n" +
                "bulumce (106) -> xeeor, lqyjcz, lsnyoe\n" +
                "dvqaks (35) -> mkzpyhk, excue, zxuzl, ukdun, xoxpsax, opyyn, unvguy\n" +
                "zbredd (391) -> micrqvl, yhjbr\n" +
                "rwulpg (106) -> uymdisn, xhgxlcb\n" +
                "rfigs (89)\n" +
                "xoxpsax (970) -> lmteo, htzab, vllceh, zotuf, xjjge\n" +
                "ygfdkew (34)\n" +
                "ahhtyrn (12)\n" +
                "ckeqik (12)\n" +
                "lqyjcz (87)\n" +
                "gaodhdn (421)\n" +
                "moqjbf (23)\n" +
                "wcjly (254) -> kunjdjs, bubuic\n" +
                "urdzsm (347) -> mfkyyn, pujxta\n" +
                "ipbtx (94)\n" +
                "ichsx (38)\n" +
                "cfofvmk (54)\n" +
                "slavm (402)\n" +
                "oakblco (32)\n" +
                "pbsabz (11)\n" +
                "ffsrpnc (12)\n" +
                "kfabmu (85)\n" +
                "gsabtz (357) -> lbhigx, jklgnxw\n" +
                "tfjnjpk (63) -> jgcpvo, qppcrmh, gwzoe\n" +
                "nmzgz (91)\n" +
                "uante (22)\n" +
                "ruann (26)\n" +
                "tezrxeh (76)\n" +
                "usqget (73) -> jtlqjwb, uzqerzq, fadrtgp\n" +
                "rpwxt (50)\n" +
                "evacloi (83)\n" +
                "cqoxq (275)\n" +
                "pnfmax (46)\n" +
                "pdqytmz (99)\n" +
                "hidxzy (50)\n" +
                "zrjkvhs (70) -> yxqgha, tlghvt\n" +
                "kaahj (67)\n" +
                "qkoobj (784) -> puiuyvv, imkbcte, zroqzf\n" +
                "uynuje (69)\n" +
                "wgrjyq (285) -> iwajq, adqhyes\n" +
                "etgphv (31)\n" +
                "tdfla (393) -> ussukc, ismlzl\n" +
                "qzdgae (9)\n" +
                "aivthrm (87)\n" +
                "kfjckfy (217)\n" +
                "nxydw (159) -> hrykmkz, mkidiho\n" +
                "bkxeyq (72)\n" +
                "inbnp (180) -> ueadckf, udnuwf\n" +
                "bdvlgx (543) -> psrozz, efjmuv, plxbhxv\n" +
                "pfdhuej (88)\n" +
                "ekojj (148) -> oeuror, ajhda\n" +
                "srvqwf (112) -> rbjjv, duhoci\n" +
                "hvyklmf (9)\n" +
                "bvpxgrf (80) -> mnryxre, orjnwip, qaznr\n" +
                "reyxj (84)\n" +
                "yqzlm (37) -> ptkhfgv, ikmdjtw\n" +
                "yhwiyr (62)\n" +
                "ygdeams (32) -> rhomwvz, gwnlt\n" +
                "jvrfyh (100) -> mvbiu, druuhf\n" +
                "zlwjs (81)\n" +
                "oogzvyg (23)\n" +
                "rkauf (32)\n" +
                "hfbyx (247) -> wldfv, xmufd\n" +
                "jtokzu (35)\n" +
                "hyeteeg (367) -> xwmmdbs, vhpqug\n" +
                "gowyxis (313) -> vtazp, opdzu\n" +
                "riszb (81)\n" +
                "hgzal (59) -> imslgj, bktvx\n" +
                "lvxmqy (90) -> lyyqnt, gwcznw, fdlcwg, nifsnge\n" +
                "bihjuos (40)\n" +
                "mnryxre (36)\n" +
                "nkaejjm (32)\n" +
                "wxgwp (88) -> ofeqif, dxdioa\n" +
                "oqxprqa (1180) -> ntigfy, mnfrudu, uknimmc, qnljlu\n" +
                "jbeccla (46)\n" +
                "tlikogo (25) -> lldzrv, qkcqxgk, wpypfwm, hbwuw\n" +
                "easqs (41)\n" +
                "jtemqlc (320)\n" +
                "wldfv (16)\n" +
                "gcepcso (498) -> ekojj, owexd, fypjed, epxsima, ppoiun\n" +
                "vtbkd (123)\n" +
                "cfwrezj (254) -> tjpyzok, uoowx\n" +
                "bqcpk (7)\n" +
                "wkrunp (49)\n" +
                "lsnyoe (87)\n" +
                "diodh (98)\n" +
                "etidy (167) -> hqpzd, ponhl\n" +
                "qucjvpl (6)\n" +
                "xzzbz (629) -> ifcvuo, ojvela, vdmezfc\n" +
                "lfbocy (174)\n" +
                "mgqrq (95)\n" +
                "hykydhr (91)\n" +
                "ohseo (96)\n" +
                "udjinl (78)\n" +
                "yeoia (5411) -> kwlrqf, sabrmjw, whvre\n" +
                "tvcul (96)\n" +
                "hqept (94)\n" +
                "ljyobyk (77) -> zvmbtmg, pdqytmz\n" +
                "mwixu (117) -> fcqyuld, cssuc\n" +
                "qssip (34)\n" +
                "zmuxeo (74)\n" +
                "uvfrdrm (12966) -> kvqdnuq, zelat, bheam\n" +
                "duhoci (69)\n" +
                "ccvnkwy (92) -> dpbwe, zsflxau\n" +
                "lfgtisg (39)\n" +
                "exgjf (50)\n" +
                "uivov (22)\n" +
                "cvvewfi (13)\n" +
                "mkzpyhk (1136) -> podrlpj, tyddsz, tkxpetn, pnmuf\n" +
                "sxeywx (84)\n" +
                "cbezt (388) -> cjcnjv, gcdvju\n" +
                "dvpfc (28)\n" +
                "eounhdc (97) -> otsbf, ytvur, lilwts\n" +
                "gxflenw (73)\n" +
                "ncewylf (1774) -> btuuf, omzdv\n" +
                "uftzqia (39)\n" +
                "ulxsfq (340) -> hmphp, ivrypwl, duwrtfc, vpcawb\n" +
                "uebysc (47)\n" +
                "tkxpetn (93) -> oxtuf, ytrmqx\n" +
                "qgjpqt (48)\n" +
                "dmrvdna (27)\n" +
                "qrfqnq (81)\n" +
                "ykuovbf (93)\n" +
                "hdiot (14)\n" +
                "oraqucr (279)\n" +
                "wruuxfs (58) -> zlwjs, pfahvas, mtrumh\n" +
                "odnxu (12)\n" +
                "bttuf (42)\n" +
                "cpvmv (83)\n" +
                "huxkort (327) -> dczlybu, cdeyujt, idhmz\n" +
                "prtbd (38) -> ipgte, tkjlunx, fusrhst, btnvmr\n" +
                "krvxpc (424) -> ssqhzgo, xkljy, pjflk, lfkqyf, cksrupl, qivrvv\n" +
                "pcjppf (203) -> hcyhv, cedao\n" +
                "vdmezfc (399)\n" +
                "gvqhsi (88)\n" +
                "sjrlj (66)\n" +
                "lotiapl (90)\n" +
                "xtprb (52)\n" +
                "ofqyppk (186) -> ymmmd, oysettw, ceahs, tqjnarz, vyoxvhv\n" +
                "umwyc (78)\n" +
                "etyllx (53) -> rclzxhh, qoybzxh, easqs\n" +
                "yeogm (76)\n" +
                "iujht (83)\n" +
                "bfvriie (48)\n" +
                "cfqpega (94)\n" +
                "mikabr (170) -> zmksfeg, houiwqu\n" +
                "aawuak (32)\n" +
                "zbizc (99) -> ygivo, fmxgjyi, lwgppcz, kaahj\n" +
                "nwyys (50)\n" +
                "ztcoo (88)\n" +
                "linzo (64)\n" +
                "gfdnli (84)\n" +
                "gahmpi (52)\n" +
                "waikg (275)\n" +
                "ednoc (61)\n" +
                "namjj (29) -> umwyc, uocgh\n" +
                "mbfhki (39)\n" +
                "gnbmemw (123)\n" +
                "wamfc (48)\n" +
                "geevgr (83)\n" +
                "gnpvu (20) -> mmvft, pcjppf, jajnukt, dochk, hngooro, gaepxdo\n" +
                "ffmjzxx (28)\n" +
                "aiifb (54)\n" +
                "hcyhv (43)\n" +
                "zmksfeg (64)\n" +
                "eilacwy (60)\n" +
                "yxqgha (52)\n" +
                "posqxf (71)\n" +
                "rujthq (92)\n" +
                "fppes (65)\n" +
                "fmtsssj (75)\n" +
                "nnrup (93)\n" +
                "agwseuk (132) -> jicds, ntintc, odlmlp\n" +
                "vxbul (24)\n" +
                "wqwuh (98)\n" +
                "vgfqulp (199) -> mzdorez, ibygguq\n" +
                "idbcgs (175) -> xkvgp, buzeugv\n" +
                "hrxzek (184) -> skdiibp, lgjtrj, awhtxfi\n" +
                "tzseb (23) -> legxh, kfabmu, ihrnca\n" +
                "mnzrs (93)\n" +
                "hqvgt (129)\n" +
                "stfsit (96)\n" +
                "fetmzid (15)\n" +
                "hydsxwk (74)\n" +
                "orysz (40) -> xjkgmv, knvtk\n" +
                "snblhv (99)\n" +
                "ivrypwl (16)\n" +
                "vyvwpp (13)\n" +
                "opxgy (7)\n" +
                "xeeor (87)\n" +
                "zsflxau (50)\n" +
                "bheam (2222) -> iitdq, aszlk, qucjvpl\n" +
                "wygvotv (16)\n" +
                "ixukvkp (92) -> uaouhj, yotjazk\n" +
                "ltxdrag (9)\n" +
                "pfvxd (89)\n" +
                "dpulvm (1175) -> xuuft, xwblt, invrcvf\n" +
                "oyatv (57)\n" +
                "atwpqc (35)\n" +
                "brbzgkm (16)\n" +
                "ijvmnd (12)\n" +
                "wqqqxn (18) -> aqivm, ozyiajn, gcgai\n" +
                "fbbhkn (84) -> pomtfpc, rjqihtl\n" +
                "xuuft (41) -> kjxwxam, dvcdvss\n" +
                "zohacp (1362) -> ovsdyv, yuwdebl, jnkqo, topcav, jsbcdkn, azgii\n" +
                "tsejpo (90) -> bzpuxrk, goyce, rwbqd, mfrcmg\n" +
                "ioinp (77)\n" +
                "zjdlixd (831) -> mpfrdzc, oazne, zwyrvyj, vebkntv, nxydw\n" +
                "sfkznca (74)\n" +
                "cvddep (12)\n" +
                "mnegwoy (47)\n" +
                "mkidiho (59)\n" +
                "xyorm (38)\n" +
                "rpcpu (34)\n" +
                "akqzb (79)\n" +
                "rmuwew (71)\n" +
                "glton (7)\n" +
                "hpvsz (57)\n" +
                "oauxbtz (23)\n" +
                "gvibwl (52)\n" +
                "hwssapq (78)\n" +
                "ecyyqsp (80) -> qwuwet, cgoclfi\n" +
                "pzxhj (145) -> qhwwv, ktiqs\n" +
                "rbjjv (69)\n" +
                "twsfo (151) -> namjj, jnetreq, wcsvv, lglzu\n" +
                "dzihru (11994) -> bwvmae, kpfjraq, ztxlw, bboim\n" +
                "rybeva (11)\n" +
                "imslgj (65)\n" +
                "odgaq (172) -> tpdpnu, ebpdat\n" +
                "rutvyr (9470) -> ecuhckx, morwid, daziqx\n" +
                "yacsqd (33) -> hlxglt, cbxczib, rxeesk, kgepl\n" +
                "adqhyes (89)\n" +
                "uprknk (65) -> ccezie, eycqvhz\n" +
                "wpypfwm (81)\n" +
                "oxhcne (38)\n" +
                "xggqps (95)\n" +
                "lojhip (66)\n" +
                "qivrvv (61) -> gqwepeb, chfkzye\n" +
                "rlgru (32)\n" +
                "cqiexk (154) -> pjdwe, awgit, kqxvtvy\n" +
                "fctrumt (259) -> yixnd, rpqtlka\n" +
                "vwfoi (34)\n" +
                "xxrxf (84)\n" +
                "ayhtq (10)\n" +
                "whvre (2803) -> olnfb, mgqrq\n" +
                "cbxczib (97)\n" +
                "zusipm (188)\n" +
                "kdsrjc (142) -> ytjvi, kloyc, oghvi, hbdmcdc\n" +
                "cxfsjtx (50)\n" +
                "unvguy (761) -> unwxho, jgemyt, fsaitmn\n" +
                "qtwwe (14)\n" +
                "zjybiy (47)\n" +
                "nygkk (857) -> zatst, ixukvkp, rxlcg, wqcsaan, ccvnkwy, ahxil\n" +
                "lowahta (36)\n" +
                "rscybf (7)\n" +
                "ytrmqx (84)\n" +
                "odkjk (108) -> nwvfh, xquwlv, hghuq, dsozgr\n" +
                "cedao (43)\n" +
                "ehfoqf (66)\n" +
                "knfjqwi (280) -> mazuw, mtjcvaf\n" +
                "obidwky (36)\n" +
                "pclycj (13)\n" +
                "vhlpr (17)\n" +
                "vjzzaxi (47)\n" +
                "coleqoq (96)\n" +
                "yeavyk (50)\n" +
                "idvkl (29)\n" +
                "rwbqd (52)\n" +
                "sppbe (23)\n" +
                "rjqihtl (46)\n" +
                "wqcsaan (114) -> dkorpg, lwbtd\n" +
                "lugxki (83)\n" +
                "gnmkuj (75)\n" +
                "tyfoz (94)\n" +
                "ubhvop (89)\n" +
                "fpmomi (94)\n" +
                "nzdilp (99)\n" +
                "uoybmh (349)\n" +
                "tdfetjs (35)\n" +
                "ziclyc (75)\n" +
                "zpaybpo (81)\n" +
                "mbqewjq (250)\n" +
                "jrnzibg (370) -> rantzzr, vwjspf, iguatq\n" +
                "bjllzfr (10)\n" +
                "pbvmk (125) -> xzufzqj, jtokzu, jfagd, atwpqc\n" +
                "cnzqk (15)\n" +
                "btnvmr (85)\n" +
                "xlhbak (14779) -> hqvgt, zhxqgb, vvyffam, uwqgz\n" +
                "qpshatb (95)\n" +
                "otzkp (92)\n" +
                "stzau (81)\n" +
                "pxheyj (16)\n" +
                "zakeqj (7554) -> ltmvmwe, pnptpzb, gcepcso, bdvlgx, yhwegl, uyauqa\n" +
                "jkeqz (34)\n" +
                "ckkcgp (180) -> nzdilp, snblhv\n" +
                "vyeryld (9)\n" +
                "wxqcjau (39)\n" +
                "cssuc (69)\n" +
                "gicuckw (80)\n" +
                "fxftvvi (989) -> fcydda, tfjnjpk, etidy\n" +
                "ltmvmwe (756) -> qavfiun, yqcaue, ybppeq\n" +
                "kyjva (16122) -> wqqqxn, vkzmdxf, twsfo, iwrxh\n" +
                "jflahw (84) -> tjiqr, csjgde, wgrjyq, hyeteeg, gwlskzx, fuohb\n" +
                "cbxgol (42)\n" +
                "xwblt (193)\n" +
                "podrlpj (261)\n" +
                "kfibzch (54) -> dpnlf, mcjbnfk, blmrnzv\n" +
                "bdvlhq (21) -> akqzb, fcajmx, smubum\n" +
                "qhwwv (60)\n" +
                "ubsmxso (78)\n" +
                "mtpjiqv (42)\n" +
                "ccezie (95)\n" +
                "fgeakc (93)\n" +
                "jyppp (57)\n" +
                "sqvfg (56)\n" +
                "ycbensb (95)\n" +
                "rznjgnj (40)\n" +
                "luobrd (36)\n" +
                "uknimmc (235) -> gyeakaj, sxeywx\n" +
                "zzsqh (78)\n" +
                "lvheqh (261) -> jtviekh, kyeul\n" +
                "koeufzg (84)\n" +
                "ggbavjl (21) -> iojwg, xykzz, pjvsofk, lxugv\n" +
                "crbuu (61)\n" +
                "ubpoy (68)\n" +
                "opdzu (15)\n" +
                "dfdawtk (81)\n" +
                "edafmn (27)\n" +
                "rndpfmx (300) -> pgxiavo, cjlll\n" +
                "iuzmx (16)\n" +
                "dsozgr (81)\n" +
                "ygivo (67)\n" +
                "tqytfku (79) -> krnlmf, dxyhphf\n" +
                "pmiwkyg (41)\n" +
                "bwvmae (33) -> piyyydu, ckkcgp, mqzkogj, adjeeo, uztqohr\n" +
                "xlcwwh (30)\n" +
                "auvkftn (282) -> nwvfcq, ophcgq\n" +
                "qdmwhp (93)\n" +
                "ztfmse (35)\n" +
                "ppzdpud (68) -> cbxgol, goohdry\n" +
                "ibnunt (56)\n" +
                "qdwhkl (72)\n" +
                "bktvx (65)\n" +
                "eftexh (88)\n" +
                "jwmobb (27)\n" +
                "dgjtv (29)\n" +
                "cjxuwe (47)\n" +
                "mbxffzk (210) -> vwfoi, oqatq\n" +
                "omzdv (36)\n" +
                "jsbcdkn (61) -> eumtjne, venip, aqljjk\n" +
                "qnljlu (83) -> ctmsdh, arxafnu, ximwl, xkzympi\n" +
                "uhdvcqk (46)\n" +
                "ncuauw (14760) -> xezfgjm, tjtrq, qkoobj\n" +
                "morwid (92) -> trkehb, uclzd, umrlyk, axrrydf, tjqdup, bdvlhq\n" +
                "intews (29)\n" +
                "lterpwd (34) -> bwmlg, intews\n" +
                "kunjdjs (62)\n" +
                "jlxwojc (7)\n" +
                "buodib (29)\n" +
                "qzegoz (28) -> wiprvsa, vyksxor\n" +
                "bwjrqs (56) -> wvamomg, sjrlj\n" +
                "opoav (61)\n" +
                "dpbwe (50)\n" +
                "dssmkge (93)\n" +
                "ajhda (31)\n" +
                "qjnow (217) -> rpwxt, exgjf, nwyys\n" +
                "efjmuv (195) -> lhldwh, bcyndtf, llbrfnj, kxrwmdo\n" +
                "eeozf (11)\n" +
                "vpcawb (16)\n" +
                "jpbod (51)\n" +
                "qavfiun (114) -> fmtsssj, pffjr\n" +
                "ywbagad (51)\n" +
                "tetvxtx (46)\n" +
                "ukdun (568) -> bvytf, prayxh, sjjfw, lxwaq\n" +
                "qhivkhk (12)\n" +
                "ndjueqb (29)\n" +
                "swzwgb (25)\n" +
                "mrfxp (54)\n" +
                "qkcqxgk (81)\n" +
                "lfmuqxa (98) -> dmrvdna, tfrrlt\n" +
                "tkjlunx (85)\n" +
                "avcwghu (94)\n" +
                "jgcpvo (72)\n" +
                "uzqerzq (90)\n" +
                "qoybzxh (41)\n" +
                "ldghw (48)\n" +
                "lrjdbp (23)\n" +
                "hfejf (13)\n" +
                "invrcvf (49) -> bkxeyq, qdwhkl\n" +
                "opyyn (1428) -> zusipm, bvpxgrf, wdvmvhe, bwjrqs\n" +
                "amkkd (51)\n" +
                "vnxmgtu (91)\n" +
                "pnednw (60)\n" +
                "eumtjne (60)\n" +
                "siknup (52)\n" +
                "xwukld (97)\n" +
                "mxpnyo (81)\n" +
                "iojwg (93)\n" +
                "daziqx (1364) -> orysz, lterpwd, yhkvwfh\n" +
                "nlusd (23)\n" +
                "zviebo (213) -> bpndvbp, vrpls, zeauw, uante\n" +
                "ntigfy (376) -> vyeryld, jgzpdx, hvyklmf\n" +
                "atzunin (78)\n" +
                "bcyndtf (35)\n" +
                "iitdq (6)\n" +
                "azgii (241)\n" +
                "qmkyl (12)\n" +
                "dlzrs (18)\n" +
                "zqcrxm (135) -> pclycj, hfejf, hpcvu\n" +
                "vbklmbl (298)\n" +
                "jlmbjs (52)\n" +
                "yrbypdv (89)\n" +
                "vyoxvhv (24) -> eplazoz, ftkqwci, xgrkt, nedbp\n" +
                "eadmwtp (22)\n" +
                "kqxvtvy (71)\n" +
                "hpcvu (13)\n" +
                "yuwdebl (67) -> fhsmv, eifgc\n" +
                "tucqq (80) -> vpywo, vuoipf, jflahw, fgpgrxr, tgctm\n" +
                "yfjodsp (373) -> bjllzfr, ldjeual\n" +
                "ulgusti (82)\n" +
                "yvbxww (24)\n" +
                "ihbybvd (84) -> eptpjci, gicuckw, mjfmma, ayismka\n" +
                "ciygx (23)\n" +
                "iwajq (89)\n" +
                "bncdukb (79) -> fwjrt, ftbzlvu\n" +
                "zhxqgb (115) -> tdvopw, jlxwojc\n" +
                "efnhbg (39)\n" +
                "pgiscip (30) -> gxflenw, bpaelk\n" +
                "jnmwff (233) -> tyfoz, hqept\n" +
                "gwnlt (72)\n" +
                "bybrtqt (13)\n" +
                "buzeugv (17)\n" +
                "jzsuszj (37)\n" +
                "dbwnqzs (25)\n" +
                "illkoi (56)\n" +
                "tlghvt (52)\n" +
                "vglmk (96) -> iygpmov, dtrdb, rndpfmx, cfwrezj, zoeyj\n" +
                "xquwlv (81)\n" +
                "lilwts (98)\n" +
                "aqivm (67) -> illkoi, gitpug, ibnunt, sqvfg\n" +
                "vsprxrs (175) -> fpxda, dyyccwq\n" +
                "ihxxanb (88)\n" +
                "semuybr (10)\n" +
                "cdeyujt (17)\n" +
                "xufnd (90)\n" +
                "etihx (97)\n" +
                "mnfrudu (207) -> agrorp, hlbyzqi\n" +
                "bfwgsag (27)\n" +
                "xjkgmv (26)\n" +
                "pnmuf (173) -> soiwb, htinuz, eadmwtp, uivov\n" +
                "rfxyb (48)\n" +
                "igeuou (99)\n" +
                "aqcvcmu (60)\n" +
                "ewomrs (84)\n" +
                "cugsuk (13)\n" +
                "weeom (20)\n" +
                "mpsbosw (91)\n" +
                "mmvft (275) -> zyxbmy, gdftnih\n" +
                "nifsnge (78)\n" +
                "lrhov (52)\n" +
                "psrozz (223) -> adccmtd, hkifor\n" +
                "enpvalm (14) -> dnkuuf, qjzipyw, znoap, bulumce, qxubf, nnktssm\n" +
                "jpxtrww (23)\n" +
                "zroqzf (264) -> rybeva, eeozf\n" +
                "nfcrpf (22)\n" +
                "jgjis (76)\n" +
                "zqbno (81) -> tlikogo, uoybmh, vgfqulp, mstjkgi, uysrm\n" +
                "fvzlw (76) -> kchxaz, hidxzy\n" +
                "legxh (85)\n" +
                "iomsug (78)\n" +
                "skdiibp (67)\n" +
                "dyyccwq (96)\n" +
                "kjxwxam (76)\n" +
                "oeuror (31)\n" +
                "lodjokj (32)\n" +
                "mvvdw (39)\n" +
                "exadxz (41) -> xxrxf, koeufzg\n" +
                "pmcxf (6)\n" +
                "oqkcc (95)\n" +
                "mbfvaw (22)\n" +
                "xbnecm (227) -> brbzgkm, iuzmx, wygvotv\n" +
                "kpfjraq (923) -> srvqwf, drfiyc, khxovd, mbqewjq\n" +
                "pfahvas (81)\n" +
                "dehctx (87)\n" +
                "bpaelk (73)\n" +
                "jndcfc (90)\n" +
                "axrrydf (190) -> tsqfitq, ffqzxf, dviucr, mroyq\n" +
                "ukcqez (129) -> paplmio, nmzgz, vnxmgtu\n" +
                "jklgnxw (32)\n" +
                "fypjed (98) -> cxhplek, ffmjzxx, dvpfc, odvadyf\n" +
                "uwxvsjd (313) -> edafmn, bfwgsag\n" +
                "iguatq (7)\n" +
                "flnwg (30) -> ghdzwq, kziosg, apijq, zohacp, dkspw, zmzmna, dqjepow\n" +
                "soscup (88)\n" +
                "lrrznr (536) -> ymmun, uxfcrt, wxgwp, kdsrjc, hkstlva\n" +
                "dkspw (918) -> prtbd, huxkort, suvtxzq, dpqsx, wcjly\n" +
                "goyce (52)\n" +
                "ueadckf (44)\n" +
                "zikbees (13)\n" +
                "uwfkgbm (98)\n" +
                "gdftnih (7)\n" +
                "zvmbtmg (99)\n" +
                "pfjaob (78)\n" +
                "gzbpbzh (10)\n" +
                "znoap (205) -> bpmgs, riszb\n" +
                "btmauhd (43)\n" +
                "eptpjci (80)\n" +
                "gejdtfw (65882) -> yeoia, fbtzaic, tbmtw, rutvyr, tucqq\n" +
                "ypujb (29)\n" +
                "hqpzd (56)\n" +
                "dtiqmg (301) -> katupgz, lrjdbp, oauxbtz, sppbe\n" +
                "yqcaue (60) -> ubpoy, feecvjl, tjvdz\n" +
                "goohdry (42)\n" +
                "glcfzs (27)\n" +
                "vllceh (173) -> cqzhj, jpxtrww, lhumvsh\n" +
                "utecy (64)\n" +
                "kzmsh (38) -> upalmon, fppes\n" +
                "iotjdfw (77)\n" +
                "vyqeu (83)\n" +
                "vmask (9)\n" +
                "ftkqwci (77)\n" +
                "tjiqr (87) -> avcwghu, cfqpega, ipbtx, fpmomi\n" +
                "polkn (91947) -> dvqaks, fnubw, xlhbak\n" +
                "xmufd (16)\n" +
                "mtrumh (81)\n" +
                "vebkntv (87) -> qdyxf, xqkov\n" +
                "ffqzxf (17)\n" +
                "mstjkgi (178) -> jyppp, oyatv, hpvsz\n" +
                "awkci (88)\n" +
                "kziosg (2380) -> pbscvh, bjhbaeq, hhvng, bucdl\n" +
                "fpuaei (23)\n" +
                "ngzhx (334) -> idvkl, dgjtv, uptfdfp\n" +
                "zfkliql (12)\n" +
                "pssff (95) -> lotiapl, jndcfc\n" +
                "bqytirn (78)\n" +
                "caaul (211) -> ehfoqf, tvvfszm, lojhip\n" +
                "wiprvsa (74)\n" +
                "lmqhg (12)\n" +
                "hhvng (73) -> vhlpr, nxalp\n" +
                "arxafnu (80)\n" +
                "svlzb (78)\n" +
                "lldzrv (81)\n" +
                "bfuell (95)\n" +
                "yhwegl (584) -> jjutz, maytl, pbwqe, rzyceeb\n" +
                "xqkov (95)\n" +
                "orfvwfh (39)\n" +
                "tqcdt (9)\n" +
                "oghvi (30)\n" +
                "qjqmpw (37) -> wezrbzw, xufnd\n" +
                "ikmdjtw (76)\n" +
                "gxqkboz (88) -> ygfdkew, etvxdqn\n" +
                "ajvtod (14)\n" +
                "efhdsgs (385)\n" +
                "iuwfcfn (46)\n" +
                "orjnwip (36)\n" +
                "kyeul (41)\n" +
                "qppcrmh (72)\n" +
                "hbdmcdc (30)\n" +
                "jcetf (126) -> lvrzba, xxieckm\n" +
                "xxieckm (94)\n" +
                "uokpbvj (223) -> exzvkxu, ruann\n" +
                "evfob (7)\n" +
                "rclzxhh (41)\n" +
                "rjkehq (163) -> fpuaei, oogzvyg\n" +
                "ximwl (80)\n" +
                "zeauw (22)\n" +
                "ophcgq (8)\n" +
                "jtviekh (41)\n" +
                "druuhf (37)\n" +
                "nedbp (77)\n" +
                "vbahlkz (133)\n" +
                "ymmun (198) -> rkauf, nkaejjm\n" +
                "mcjbnfk (75)\n" +
                "mpfrdzc (251) -> cvvewfi, zikbees\n" +
                "uemmhd (124) -> pxheyj, cwxvww\n" +
                "qyxyyfe (17)\n" +
                "qjzipyw (11) -> yrbypdv, ubhvop, ohjpwlf, pfvxd\n" +
                "fgpgrxr (2583) -> wsnnpt, vlcrv, bzoui\n" +
                "etvxdqn (34)\n" +
                "tdoxrnb (68)\n" +
                "bwmlg (29)\n" +
                "nwvfh (81)\n" +
                "dqqvw (95)\n" +
                "venip (60)\n" +
                "dxdioa (87)\n" +
                "ybppeq (224) -> weeom, aeuhflj\n" +
                "fpmlwfh (231) -> qmkyl, pfvnpy, ijvmnd, dnzvald\n" +
                "pfvnpy (12)\n" +
                "owexd (48) -> nkjocfh, qrfqnq\n" +
                "douazak (50)\n" +
                "mwmyxd (2288) -> ecyyqsp, kzmsh, rwulpg\n" +
                "fgbkh (163) -> moqjbf, mcltn, nlusd, ciygx\n" +
                "qokpqcj (542) -> ulxsfq, xprwsaf, ihbybvd\n" +
                "pgxiavo (25)\n" +
                "adccmtd (56)\n" +
                "zwyrvyj (173) -> xtprb, gvibwl\n" +
                "yghmpm (46)\n" +
                "dtovks (97)\n" +
                "ayismka (80)\n" +
                "wehkpx (90) -> zzsqh, iohhnkq, iomsug, atzunin\n" +
                "ldcqple (13659) -> qdhlpqn, nygkk, sogmnn\n" +
                "kgepl (97)\n" +
                "cnstz (176)\n" +
                "vlcrv (27) -> rtdffxh, mbfvaw, nfcrpf\n" +
                "tjqdup (258)\n" +
                "syjwi (289) -> ukgraro, glcfzs\n" +
                "fveuo (40)\n" +
                "vtazp (15)\n" +
                "fpxda (96)\n" +
                "dviucr (17)\n" +
                "fitxh (65)\n" +
                "ztocqs (40)\n" +
                "fuohb (367) -> nhkfwk, rfxyb\n" +
                "nwecq (29)\n" +
                "fadrtgp (90)\n" +
                "rtdffxh (22)\n" +
                "bbvcyqt (37)\n" +
                "gqwepeb (88)\n" +
                "xuhgu (82)\n" +
                "ghdzwq (1763) -> idbcgs, fkjnhd, ieacu, exadxz, rjkehq\n" +
                "eygeo (31)\n" +
                "qaznr (36)\n" +
                "muxff (15)\n" +
                "hmphp (16)\n" +
                "dpnlf (75)\n" +
                "zyxbmy (7)\n" +
                "tsqfitq (17)\n" +
                "nzrhyzx (432)\n" +
                "hghuq (81)\n" +
                "yotjazk (50)\n" +
                "ztxlw (1431) -> aoxyn, gnbmemw, vtbkd, tqytfku\n" +
                "lvrzba (94)\n" +
                "uptfdfp (29)\n" +
                "uztqohr (296) -> bnlxpgs, pmiwkyg\n" +
                "dcfod (93)\n" +
                "svgsp (14)\n" +
                "zculjs (60)\n" +
                "mfrcmg (52)\n" +
                "aoxyn (19) -> jlmbjs, siknup\n" +
                "pjvsofk (93)\n" +
                "zjzldd (89)\n" +
                "hngooro (139) -> xdrpbh, gnmkuj\n" +
                "wopctnz (95)\n" +
                "pbscvh (107)\n" +
                "seqpyc (88)\n" +
                "vpywo (2334) -> dtpnpq, ygdeams, cnstz\n" +
                "ldjeual (10)\n" +
                "cwxvww (16)\n" +
                "kvqdnuq (38) -> qjnow, zbizc, uwxvsjd, vsprxrs, inpzr, cqiexk\n" +
                "zotuf (132) -> jouimd, svhudk\n" +
                "iywmq (61)\n" +
                "fkjnhd (141) -> iatnsa, jkeqz\n" +
                "nnktssm (271) -> wamfc, qgjpqt\n" +
                "jpchi (82)\n" +
                "ijhxyp (27)\n" +
                "tgoxupv (60)\n" +
                "lxwaq (67) -> reyxj, iygrvcq, gfdnli, ewomrs\n" +
                "hbwuw (81)\n" +
                "pddmke (13)\n" +
                "kxbgcn (150) -> szvvmv, oxokip\n" +
                "lyyqnt (78)\n" +
                "ddnfy (82) -> gimfzpl, uwfkgbm\n" +
                "tjtrq (52) -> bncdukb, gydhlh, pzxhj, tkbvq, vlstbi, pbvmk\n" +
                "dxyhphf (22)\n" +
                "sgcszn (6)\n" +
                "nxalp (17)\n" +
                "gobaplu (52)\n" +
                "pjflk (53) -> otzkp, rujthq\n" +
                "ovyifn (298) -> baukd, gobaplu\n" +
                "lgjtrj (67)\n" +
                "qstxfxv (161) -> hdiot, qtwwe\n" +
                "pxzxws (235) -> pfjaob, ubsmxso\n" +
                "cfuudsk (87306) -> ensbsjj, zakeqj, rmzdg\n" +
                "jgzpdx (9)\n" +
                "eifgc (87)\n" +
                "tdvopw (7)\n" +
                "bhyawz (31)\n" +
                "ppmtmra (52)\n" +
                "hyupti (22)\n" +
                "hmgnq (37)\n" +
                "mqzkogj (342) -> dlzrs, ikufikz\n" +
                "htinuz (22)\n" +
                "hyblgwq (53)\n" +
                "wdvmvhe (44) -> bfvriie, ldghw, nnmsru\n" +
                "etlyn (72)\n" +
                "zmzmna (648) -> kmvtp, cbezt, nzrhyzx, yfwlsm, odkjk\n" +
                "lzovhs (78)\n" +
                "nnmsru (48)\n" +
                "gfxra (782) -> lvxmqy, ukcqez, wehkpx, slavm, ovyifn\n" +
                "mtjcvaf (20)\n" +
                "urxki (409)\n" +
                "iygpmov (46) -> jprrnd, wojfh, vhrxv, yeogm\n" +
                "lfkqyf (237)");

        weight.printBottomNodes();
        weight.solution2();

    }
}
