package test;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonConditions {
    protected WebDriver driver;
    protected static final String USER_MAIL="simplecloudforonetime@yandex.by";
    protected static final String USER_PASSWORD="testPassword";
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }
    public static void AddCookies(WebDriver driver)
    {
        Cookie cookie_bl_uid = new Cookie.Builder("_bl_uid", "zqk1eh8ewp7aL7uFmtjF4n7ewhF7").build();
        Cookie cookie_ga = new Cookie.Builder("_ga", "GA1.2.505662652.1606241702").build();
        Cookie cookie_ym_d = new Cookie.Builder("_ym_d", "1606241703").build();
        Cookie cookie_af_ss_b = new Cookie.Builder("af_ss_b", "1").build();
        Cookie cookie_ali_apache_id = new Cookie.Builder("ali_apache_id", "33.19.92.58.16062417082.143044.2").build();
        Cookie cookie_cna = new Cookie.Builder("cna", "pTtEGGTYJ0MCAYYRmNzrEG1k").build();
        Cookie cookie_e_id = new Cookie.Builder("e_id", "pt30").build();
        Cookie cookie_intl_common_forever = new Cookie.Builder("intl_common_forever", "5vqa8HMiFUi3uijfhrvAG2+LoUwyd32wwvYOafjhsDhjQ5/YeHdYFg==").build();
        Cookie cookie_isg = new Cookie.Builder("isg", "BGJi25igSXaTRVVckcZYnc8zo-jEs2bNMl0uCqz7jlWAfwL5lEO23ej-qqOD3d5l").build();
        Cookie cookie_I = new Cookie.Builder("I", "eBxU6S6HOlyyISPOBOfwourza77OSKRxnuPzaNbMiOCP_zfB5N0dBZRiHHY6C3HNh6qDR3ylR_DpBeYBqQd-nxvtsJkyaQkmn").build();
        Cookie cookie_tfstk = new Cookie.Builder("tfstk", "c68lBstTG3S760sDf4_Ws1WCD-gOZ_zFIe8XuE6D_cV9QCTVi-Z4bZzmiTbPik1..").build();
        Cookie cookie_xman_f = new Cookie.Builder("xman_f","mm6/wzTHYozNSmCdiqRpJm7jYwwtqhZlMgGE/2iCwQ+FpHWPGCy6zjpvZmyHf+G8fckMEsXqA2GmB9sHPjos1VX+4qHimVcT8B8p8jh4cMzl8ayrc+9O20PJ4fX8uTkAUxMKrBSGpPdIrrlDwekDsw6aol5NWMirsM77Bso0cyn8oq1BYtG5Wj8EDsKOg6CyTh0nsZsXSKcU21ILkULiMVPNV+SB4hPdOiSL/7mnjH13HIQNh3EeGhksoIdp/KjgCQqNGYslzPM8rtO+0XmFaRyR9H+v3I0H1xhyRVqrdxMsWpqwcT6BxzX8XHEpd9+BFhA+9nI0Yey82Eki+fDI7hCbw51XVroAL5j59j1tF0AUanltETsv7Rh+hxgBf+IrtqlnGegesUNszvvwyMAkdrXgWrDyw2FYF4dhqGs2v5WWZDqEKSfy94o+ZNKi1SbMmeJSNDtCyWkRUsiFXLPNSe/5dl/BiSnS0h9biWvwoFk=" ).build();
        Cookie cookie_xman_t = new Cookie.Builder("xman_t", "hwnmTgKW9yJB9qQnFB6uIntlrak5C2X69lHoyTJ8FuNJxY9LYFXWs3C5lNDwrCqNX3njOfISsz6AV2jpdFVyMzobUCTxv4/ivT2NGgZaRP5qeXmLI4xbtAPXDee0nubP8HMSFgF6DZwKk2xwMsVmF1JGjktzI/BS9gWRMGc8wntiOQHkSXdt3bSzPQFtg9hDvk3ND5B15qqjI5O8K4YE5Y7nj7IW9y1EieFKQZfEk/ouDWML4NxmYM2Z6TK01BNQUZU63hWOgVLw9NyX+kNeTJyUL10jtQ7CHjhB2ZSDRJ6ohs+R25kQf/33AqFAuCZjnc+XoX5wH+qG4ZDyCA+IHYLPi9nPXoIbvvEF6M1fBHLZiHql+vuYs4ArmOUfBq7tRvUqM+Lz4EQVlOHM+qpGc6RG65MBYAI4bWVzcB0CfhpNYQb5ZtH+fe06oLP3dUbuBfKVyaKnpqzsFYbidHUGIl1NVhqllQaxYoXrJ+U1MlKBIpJ6aUIMlRK/tIg9PdxXKEMSJv9xC9a4vOaW4ARDraugjZddCMBip7hUS3laPmst0eBPHf1zi3k/VqzfRIy8NjJenVobq09rMSCzmhtCL8zDEX2iLOjoD5fnYQZB9a/Aorx3lgfLCOvyu0yU6nGOtB+CXdCGpY6NXsS6/A/adoWLmqiZs7CreGGjNjMk3Y4SyVRkxj0UnRaVhKPmXNgMvs14vFYi67JshxMwCL6AWA==").build();
        Cookie cookie_xman_us_f = new Cookie.Builder("xman_us_f", "x_locale=ru_RU&x_l=0&last_popup_time=1608035876475&x_user=BY|simplecloudforonetime|user|ifm|3065125457&no_popup_today=n&x_lid=by1465765457usjae&x_c_chg=0&x_as_i=%7B%22cookieCacheEffectTime%22%3A1608045705378%2C%22isCookieCache%22%3A%22Y%22%2C%22ms%22%3A%220%22%7D&acs_rt=98eb2481d75f498980379dc8932178de").build();
        Cookie cookie_xman_us_t= new Cookie.Builder("xman_us_t","x_lid=by1465765457usjae&sign=y&rmb_pp=simplecloudforonetime@yandex.by&x_user=PaQAYe0wIYvv5NTjZaH/sEV+ayAIjzdF/gbWAs0YGi0=&ctoken=h0m4p22ooa6q&need_popup=y&l_source=aliexpress").build();
        driver.manage().addCookie(cookie_bl_uid);
        driver.manage().addCookie(cookie_ga);
        driver.manage().addCookie(cookie_ym_d);
        driver.manage().addCookie(cookie_af_ss_b);
        driver.manage().addCookie(cookie_ali_apache_id);
        driver.manage().addCookie(cookie_cna);
        driver.manage().addCookie(cookie_e_id);
        driver.manage().addCookie(cookie_intl_common_forever);
        driver.manage().addCookie(cookie_isg);
        driver.manage().addCookie(cookie_I);
        driver.manage().addCookie(cookie_tfstk);
        driver.manage().addCookie(cookie_xman_f);
        driver.manage().addCookie(cookie_xman_t);
        driver.manage().addCookie(cookie_xman_us_f);
        driver.manage().addCookie(cookie_xman_us_t);
    }
    public static double convertPriceToDouble(String price)
    {
        Pattern pattern=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pattern.matcher(price);
        int start = 0;
        double result =0;
        while (matcher.find(start)) {
            String value = price.substring(matcher.start(), matcher.end());
            value =value.replace(',', '.');
            result = Double.parseDouble(value);
            start = matcher.end();
        }
        return result;
    }
}
