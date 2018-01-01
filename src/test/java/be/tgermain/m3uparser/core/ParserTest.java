package be.tgermain.m3uparser.core;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * Created by tgermain on 30/12/2017.
 */
public class ParserTest {

    @Test
    public void testParse() throws Exception {
        List<Entry> entries = new Parser().parse(ParserTest.class.getResourceAsStream("/test.m3u"));

        Assert.assertEquals("Should have 3 entries", 3, entries.size());

        Assert.assertEquals("La Une HD", entries.get(0).getChannelName());
        Assert.assertEquals("La Une HD", entries.get(0).getTvgName());
        Assert.assertEquals("La_Une", entries.get(0).getTvgId());
        Assert.assertEquals("http://panel.globepremium.com:8000/live/xxx/yyy/84.ts", entries.get(0).getChannelUri());
        Assert.assertEquals("-1", entries.get(0).getDuration());
        Assert.assertEquals("TV", entries.get(0).getGroupTitle());
        Assert.assertEquals("La Une HD", entries.get(0).getChannelName());
        Assert.assertEquals("false", entries.get(0).getRadio());
        Assert.assertEquals("../logo/La_Une.png", entries.get(0).getTvgLogo());
        Assert.assertEquals("1", entries.get(0).getTvgShift());

        Assert.assertEquals("La Deux HD", entries.get(1).getChannelName());
        Assert.assertEquals("La Deux HD", entries.get(1).getTvgName());
        Assert.assertEquals("La_Deux", entries.get(1).getTvgId());
        Assert.assertEquals("http://panel.globepremium.com:8000/live/xxx/yyy/85.ts", entries.get(1).getChannelUri());
        Assert.assertEquals("-1", entries.get(1).getDuration());
        Assert.assertEquals("TV", entries.get(1).getGroupTitle());
        Assert.assertEquals("La Deux HD", entries.get(1).getChannelName());
        Assert.assertEquals("false", entries.get(1).getRadio());
        Assert.assertEquals("../logo/La_Deux.png", entries.get(1).getTvgLogo());
        Assert.assertEquals("2", entries.get(1).getTvgShift());

        Assert.assertEquals("RTL TVI HD", entries.get(2).getChannelName());
        Assert.assertEquals("RTL TVI HD", entries.get(2).getTvgName());
        Assert.assertEquals("RTL_TVI", entries.get(2).getTvgId());
        Assert.assertEquals("http://panel.globepremium.com:8000/live/xxx/yyy/719.ts", entries.get(2).getChannelUri());
        Assert.assertEquals("-1", entries.get(2).getDuration());
        Assert.assertEquals("TV", entries.get(2).getGroupTitle());
        Assert.assertEquals("RTL TVI HD", entries.get(2).getChannelName());
        Assert.assertEquals("false", entries.get(2).getRadio());
        Assert.assertEquals("../logo/RTL_TVI.png", entries.get(2).getTvgLogo());
        Assert.assertEquals("3", entries.get(2).getTvgShift());
    }

    @Test
    public void testParseGlobalTvgShift() {
        List<Entry> entries = new Parser().parse(ParserTest.class.getResourceAsStream("/test_global_tvg_shift.m3u"));

        Assert.assertEquals("Should have 3 entries", 3, entries.size());

        Assert.assertEquals("La Une HD", entries.get(0).getChannelName());
        Assert.assertEquals("La Une HD", entries.get(0).getTvgName());
        Assert.assertEquals("La_Une", entries.get(0).getTvgId());
        Assert.assertEquals("http://panel.globepremium.com:8000/live/xxx/yyy/84.ts", entries.get(0).getChannelUri());
        Assert.assertEquals("-1", entries.get(0).getDuration());
        Assert.assertEquals("TV", entries.get(0).getGroupTitle());
        Assert.assertEquals("La Une HD", entries.get(0).getChannelName());
        Assert.assertEquals("false", entries.get(0).getRadio());
        Assert.assertEquals("../logo/La_Une.png", entries.get(0).getTvgLogo());
        Assert.assertEquals("4", entries.get(0).getTvgShift());

        Assert.assertEquals("La Deux HD", entries.get(1).getChannelName());
        Assert.assertEquals("La Deux HD", entries.get(1).getTvgName());
        Assert.assertEquals("La_Deux", entries.get(1).getTvgId());
        Assert.assertEquals("http://panel.globepremium.com:8000/live/xxx/yyy/85.ts", entries.get(1).getChannelUri());
        Assert.assertEquals("-1", entries.get(1).getDuration());
        Assert.assertEquals("TV", entries.get(1).getGroupTitle());
        Assert.assertEquals("La Deux HD", entries.get(1).getChannelName());
        Assert.assertEquals("false", entries.get(1).getRadio());
        Assert.assertEquals("../logo/La_Deux.png", entries.get(1).getTvgLogo());
        Assert.assertEquals("4", entries.get(1).getTvgShift());

        Assert.assertEquals("RTL TVI HD", entries.get(2).getChannelName());
        Assert.assertEquals("RTL TVI HD", entries.get(2).getTvgName());
        Assert.assertEquals("RTL_TVI", entries.get(2).getTvgId());
        Assert.assertEquals("http://panel.globepremium.com:8000/live/xxx/yyy/719.ts", entries.get(2).getChannelUri());
        Assert.assertEquals("-1", entries.get(2).getDuration());
        Assert.assertEquals("TV", entries.get(2).getGroupTitle());
        Assert.assertEquals("RTL TVI HD", entries.get(2).getChannelName());
        Assert.assertEquals("false", entries.get(2).getRadio());
        Assert.assertEquals("../logo/RTL_TVI.png", entries.get(2).getTvgLogo());
        Assert.assertEquals("4", entries.get(2).getTvgShift());
    }

    @Test
    public void testParseBigFile() {
        List<Entry> entries = new Parser().parse(ParserTest.class.getResourceAsStream("/test_big_file.m3u"));
        Assert.assertEquals("Should have 9065 entries", 9065, entries.size());
    }

    @Test(expected = ParsingException.class)
    public void testEmptyStream() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(new byte[]{});
        new Parser().parse(bais);
    }

    @Test(expected = ParsingException.class)
    public void testWrongStream() throws IOException {
        InputStream stream = ParserTest.class.getResourceAsStream("/test_global_tvg_shift.m3u");
        stream.close();
        new Parser().parse(stream);
    }
}


