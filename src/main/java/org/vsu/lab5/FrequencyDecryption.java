package org.vsu.lab5;

import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class FrequencyDecryption {

    private static final Logger logger = LoggerFactory.getLogger(FrequencyDecryption.class);

    public static void main(String[] args)
    {
        String alphabetFreqRus = "oианеврлтмыдкcпзхяйгбжушчьщцюэъф";
        String alphabetFreqEng = "etiorsnahcldfpuymgwbvxjqkz";

        String codedMessageRus = "щ зсдлъэд фцяб цоцюъбк сня пбь емйюсцдъ й цяъцк йюцсцъб йюмсэъъбжэ яцжмжэ, м й ясыфцк - яьэъъбж цяъцгюмлъбж еямъэдж зця цяъцк исбудк, ъдйжцюсн ъм юц, аюц цъц зсэъмяьдлмьц ядйнюимж щьмядьчшдщ. эе щйдо гюэо еямъэк юцьчиц ящм яцжм пбьэ лэьбжэ: яцж, фяд фцйюэъэшм \"ицъюэъдъюмьч\", ям йюцнвэк сняцж й ъэж юсмиюэс дфцсцщм, еъмждъэюбк йщцэжэ пьэъмжэ. цйюмьчъцд щйд ьмщиэ, щзьцюч яц ющдсйицк. юсмиюэс дфцсцщм ицфям - юц зсэъмяьдлмь щцсцъэъы, э ъм щбщдйид пбьм эецпсмлдъм щцсцъм, ядслмвмн щ иьтщд пьэъ. щйд ьмщиэ цоцюъцфц сням пбьэ жнйъбд, сбпъбд, м зця ъэжэ едьдъъбд зцящмьб. емяъэд ящдсэ ьмщци щбоцяэьэ ъм цфсцжъбк ящцс - жцъдюъбк, ими дфц ъмебщмьэ эеясдщьд. ъм ъдж пбьэ юцлд цяъцгюмлъбд жнйъбд, лэщцсбпъбд э нэаъбд ьмщиэ, м зцйсдяэъд--ящыогюмлъбк \"жцъдюъбк\" юсмиюэс.щ емяъдк амйюэ ящцсм -сня ймсмтуди й зцфсдпмжэ э иьмяцщбжэ, иэудщуэжэ зцьаэвмжэ исбй. цоцюъбк сня зцьыаэь йщцд ъмещмъэд двд щ юд щсдждъм, ицфям еядйч смесдудъц пбьц юцсфцщмюч яэачт, зсэъцйэжцк зцяжцйицщъбжэ цоцюъэимжэ. щздсдяэ ьмщци, ъм зьцвмяэ, щяцьч уэсцицфц юсцюымсм, йюцньэ здсдъцйъбд змьмюиэ э юцьзэьэйч юцсфцщшб й ицсеэъмжэ э ждуимжэ, ъмзцьъдъъбжэ щйдщцежцлъбжэ зсцяыиюмжэ. оцяэьэ цоцюъэиэ, цпщдумъъбд ыюимжэ, юдюдсимжэ, емкшмжэ.ы пмп эе ицсеэъ юцсамьэ фцьцщб иыс э шбзьню, щ ждуимо щэелмьэ зцсцйнюм, ицюцсбо зсцямщшб, щбъэжмн эе ждуим,аюцпб зцимемюч зциызмюдьт, ъдзсдждъъц зцяъэжмьэ ъмя фцьцщцк, ядслм ем йщнемъъбд емяъэд ъцфэ. ъм жцйюцщцк здсдя змьмюимжэ йъцщмьэ зэсцлъэиэ, пьэъъэиэ, юцсфцщшб фсдаъдщэимжэ, лмсдъъбжэ ъм зцйюъцж жмйьд. йпэюдъвэиэ смеьэщмьэ, зц ицздкид ем йюмимъ, фцснаэк йпэюдъч -ьтпэжбк юцфям ждяцщбк ъмзэюци, йцфсдщмщуэк эещцеаэицщ э йьылмвэо, емждсемщуэо щ оцьцяъбо ьмщимо.ьдюцж йпэюдъвэицщ йждъньэ юцсфцщшб ищмймжэ, э ймжбк ьтпэжбк эе ъэо пбь фсыудщбк, эе щмсдъбо фсыу, ицюцсбд щ жцадъцж щэяд ьдлмьэ яьн зсцямлэ зэсмжэямжэ ъм ьцюимо, м ищмй адсзмьэ эе щдясм исылимжэ.жнйъбд э сбпъбд ьмщиэ йцйюцньэ эе ящыо цюядьдъэк. щ здсщцж ьдлмьц ъм зцьимо жнйц смеъбо йцсюцщ -яэач, иысб, фыйэ, эъядкиэ, змьдъбд зцсцйнюм яьн лмсицфц э щ ьдянъбо щмъъмо -пдьбд зцсцйнюм яьн емьэщъцфц.ъм истачно зц йюдъмж пбьэ смещдумъб юыуэ пмсмуицщ э зцдъъбо жцьцицж юдьню, м щдйч зцюцьци емъню цицсцимжэ щйдщцежцлъбо смеждсцщ э зсэфцюцщьдъэк--ицзадъбо, щмсдъбо, зсцщдйъбо.щц щюцсцж цюядьдъээ, юджъцж, цйщдвдъъцж юцьчиц ящдсчт щц ящцс, щэйдьэ ядйнюиэ жнйъбо юыу. зця щйджэ ьмщимжэ-- зцящмьб.цоцюъбк сня пбщмь цйцпдъъц цлэщьдъъбж здсдя пцьчуэжэ зсмеяъэимжэ. и ьмщимж зцяхделмьэ ъм юбйнаъбо сбймимо смйрсмъадъъбд иызаэоэ, э ем ъэжэ йьылмвэд щбъцйэьэ эе ьмщци";
        String codedMessageEng = "pyt viqebov, xp q bqcvmc oxgvmzv jylof myc bvtexc tyrqocr-ptvv tvfxzctxhlcxym yp cwv btystqe hr qoo cwyzv jwy tvgvxnv gybxvz fxtvgcor yt xmfxtvgcor cwtylsw ryl, cwvm cwv ymor jqr ryl gylof zqcxzpr hycw xc qmf cwxz oxgvmzv jylof hv cy tvptqxm vmcxtvor ptye fxzctxhlcxym yp cwv btystqe. xp qmr bytcxym yp cwxz zvgcxym xz wvof xmnqoxf yt lmvmpytgvqhov lmfvt qmr bqtcxgloqt gxtglezcqmgv, cwv hqoqmgv yp cwv zvgcxym xz xmcvmfvf cy qbbor qmf cwv zvgcxym qz q jwyov xz xmcvmfvf cy qbbor xm ycwvt gxtglezcqmgvz.xc xz myc cwv bltbyzv yp cwxz zvgcxym cy xmflgv ryl cy xmptxmsv qmr bqcvmcz yt ycwvt btybvtcr txswc goqxez yt cy gymcvzc nqoxfxcr yp qmr zlgw goqxez; cwxz zvgcxym wqz cwv zyov bltbyzv yp btycvgcxms cwv xmcvstxcr yp cwv ptvv zypcjqtv fxzctxhlcxym zrzcve, jwxgw xz xebovevmcvf hr blhoxg oxgvmzv btqgcxgvz. eqmr bvybov wqnv eqfv svmvtylz gymctxhlcxymz cy cwv jxfv tqmsv yp zypcjqtv fxzctxhlcvf cwtylsw cwqc zrzcve xm tvoxqmgv ym gymzxzcvmc qbboxgqcxym yp cwqc zrzcve; xc xz lb cy cwv qlcwyt/ fymyt cy fvgxfv xp wv yt zwv xz jxooxms cy fxzctxhlcv zypcjqtv cwtylsw qmr ycwvt zrzcve qmf q oxgvmzvv gqmmyc xebyzv cwqc gwyxgv. cwxz zvgcxym xz xmcvmfvf cy eqdv cwytylswor govqt jwqc xz hvoxvnvf cy hv q gymzvalvmgv yp cwv tvzc yp cwxz oxgvmzv. xp cwv fxzctxhlcxym qmf/ yt lzv yp cwv btystqe xz tvzctxgcvf xm gvtcqxm gylmctxvz vxcwvt hr bqcvmcz yt hr gybrtxswcvf xmcvtpqgvz, cwv ytxsxmqo gybrtxswc wyofvt jwy boqgvz cwv btystqe lmfvt cwxz oxgvmzv eqr qff qm viboxgxc svystqbwxgqo fxzctxhlcxym oxexcqcxym vigolfxms cwyzv gylmctxvz, zy cwqc fxzctxhlcxym xz bvtexccvf ymor xm yt qeyms gylmctxvz myc cwlz vigolfvf. xm zlgw gqzv, cwxz oxgvmzv xmgytbytqcvz cwv oxexcqcxym qz xp jtxccvm xm cwv hyfr yp cwxz oxgvmzv. cwv ptvv zypcjqtv pylmfqcxym eqr blhoxzw tvnxzvf qmf/ yt mvj nvtzxymz yp cwv svmvtqo blhoxg oxgvmzv ptye cxev cy cxev.zlgw mvj nvtzxymz jxoo hv zxexoqt xm zbxtxc cy cwv btvzvmc nvtzxym, hlc eqr fxppvt xm fvcqxo cy qfftvzz mvj btyhovez yt gymgvtmz. vqgw nvtzxym xz sxnvm q fxzcxmslxzwxms nvtzxym mlehvt. xp cwv btystqe zbvgxpxvz q nvtzxym mlehvt yp cwxz oxgvmzv jwxgw qbboxvz cy xc qmf \"qmr oqcvt nvtzxym\", ryl wqnv cwv ybcxym yp pyooyjxms cwv cvtez qmf gymfxcxymz vxcwvt yp cwqc nvtzxym yt yp qmr oqcvt nvtzxym blhoxzwvf hr cwv ptvv zypcjqtv pylmfqcxym.xp cwv btystqe fyvz myc zbvgxpr q nvtzxym mlehvt yp cwxz oxgvmzv, ryl eqr gwyyzv qmr nvtzxym vnvt blhoxzwvf hr cwv ptvv zypcjqtv pylmfqcxym. xp ryl jxzw cy xmgytbytqcv bqtcz yp cwv btystqe xmcy ycwvt ptvv btystqez jwyzv fxzctxhlcxym gymfxcxymz qtv fxppvtvmc, jtxcv cy cwv qlcwyt cy qzd pyt bvtexzzxym.pyt zypcjqtv jwxgw xz gybrtxswcvf hr cwv ptvv zypcjqtv pylmfqcxym, jtxcv cy cwv ptvv zypcjqtv pylmfqcxym; jv zyevcxevz eqdv vigvbcxymz pyt cwxz. ylt fvgxzxym jxoo hv slxfvf hr cwv cjy syqoz yp btvzvtnxms cwv ptvv zcqclz yp qoo fvtxnqcxnvz yp ylt ptvv zypcjqtv qmf yp btyeycxms cwv zwqtxms qmf tvlzv yp zypcjqtv svmvtqoor. hvgqlzv cwv btystqe xz oxgvmzvf ptvv yp gwqtsv, cwvtv xz my jqttqmcr pyt cwv btystqe, cy cwv vicvmc bvtexccvf hr qbboxgqhov oqj. vigvbc jwvm ycwvtjxzv zcqcvf xm jtxcxms cwv gybrtxswc wyofvtz qmf/ yt ycwvt bqtcxvz btynxfv cwv btystqe \"qz xz\" jxcwylc jqttqmcr yp qmr dxmf, vxcwvt vibtvzzvf yt xeboxvf, xmgolfxms, hlc myc oxexcvf cy, cwv xeboxvf jqttqmcxvz yp evtgwqmcqhxoxcr qmf pxcmvzz pyt q bqtcxgloqt bltbyzv.cwv vmcxtv txzd qz cy cwv alqoxcr qmf bvtpyteqmgv yp cwv btystqe xz jxcw ryl.zwylof cwv btystqe btynv fvpvgcxnv, ryl qzzlev cwv gyzc yp qoo mvgvzzqtr zvtnxgxms, tvbqxt yt gyttvgcxym";

        List<Pair<Character, Integer>> frequencyCodedRus = countFrequency(codedMessageRus);
        List<Pair<Character, Integer>> frequencyCodedEng = countFrequency(codedMessageEng);

        String decodedMessageRus = decodeByFrequency(codedMessageRus, frequencyCodedRus, alphabetFreqRus);
        logger.info("Encoded Message RUS: {}", codedMessageRus);
        logger.info("Decoded Message RUS: {}", decodedMessageRus);

        String decodedMessageEng = decodeByFrequency(codedMessageEng, frequencyCodedEng, alphabetFreqEng);
        logger.info("\nEncoded Message ENG: {}", codedMessageEng);
        logger.info("Decoded Message ENG: {}", decodedMessageEng);
    }

    private static List<Pair<Character, Integer>> countFrequency(String text)
    {
        List<Character> specialSymbols = Arrays.asList(' ','.',',','(',')','-','\r','\n','«','»','—',':','\"');
        Map<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < text.length(); i++)
        {
            char currentChar = text.charAt(i);
            if (!specialSymbols.contains(currentChar))
            {
                if (frequency.containsKey(currentChar))
                {
                    frequency.put(currentChar, frequency.get(currentChar) + 1);
                }
                else
                {
                    frequency.put(currentChar, 1);
                }
            }
        }

        List<Pair<Character, Integer>> frequencyList = new ArrayList<>();
        for (Map.Entry<Character, Integer> freqEntry: frequency.entrySet()) {
            frequencyList.add(new Pair<>(freqEntry.getKey(), freqEntry.getValue()));
        }

        frequencyList.sort((p1, p2) -> p2.getValue().compareTo(p1.getValue()));
        return frequencyList;
    }

    public static String decodeByFrequency(String text, List<Pair<Character, Integer>> codedAlphabet, String realAlphabet)
    {
        StringBuilder keys = new StringBuilder();
        StringBuilder decodedMessage = new StringBuilder();

        for (Pair<Character, Integer> characterIntegerPair : codedAlphabet) {
            keys.append(characterIntegerPair.getKey());
        }

        for (int i = 0; i < text.length(); i++)
        {
            var index = keys.toString().indexOf(text.charAt(i));

            if (index < 0)
            {
                decodedMessage.append(text.charAt(i));
            }
            else
            {
                decodedMessage.append(realAlphabet.charAt(index));
            }
        }

        return decodedMessage.toString();
    }
}
