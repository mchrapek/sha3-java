package pl.marek.sha3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.marek.sha3.utils.HexTools;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class Sha3TextTest {

    @Parameterized.Parameters(name = "{index}: Test data = {0}, algorithm = {1}, result = {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"WTF", Type.SHA3_224, "72E0307FC0748DBA19D64448A5BC0600B9BBC118FDC9C977C05A679A"},
                {"WTF", Type.SHA3_256, "0E2504632C3BD8366D82BEFECB74F20068CE5B6AACF4B3727E69D7E932022BB7"},
                {"WTF", Type.SHA3_384, "C0D51B359F0E02053D5CDA7D042EB4295B2EFE708D17407BF3E534F5802F53782EB245B82FC244E2F79C5302F04BD15D"},
                {"WTF", Type.SHA3_512, "87EE02079C8AAA91F15BFDA7915E4661FA10E9571F20C019B4D21F6E3407BF3055501B8889CE3E66905D7EA547D0589D380FE4F4E3DE6A7275DB69C1D043177F"},
                {"Hello World", Type.SHA3_224, "8E800079A0B311788BF29353F400EFF969B650A3597C91EFD9AA5B38"},
                {"Hello World", Type.SHA3_256, "E167F68D6563D75BB25F3AA49C29EF612D41352DC00606DE7CBD630BB2665F51"},
                {"Hello World", Type.SHA3_384, "A78EC2851E991638CE505D4A44EFA606DD4056D3AB274EC6FDBAC00CDE16478263EF7213BAD5A7DB7044F58D637AFDEB"},
                {"Hello World", Type.SHA3_512, "3D58A719C6866B0214F96B0A67B37E51A91E233CE0BE126A08F35FDF4C043C6126F40139BFBC338D44EB2A03DE9F7BB8EFF0AC260B3629811E389A5FBEE8A894"},
                {LoremIpsumText.LOREM_IPSUM_PARAGRAPH, Type.SHA3_224, "16658222DF9E42DE2A5DF4C578E22A41BA4F8CDDF8A3453AFBD41193"},
                {LoremIpsumText.LOREM_IPSUM_PARAGRAPH, Type.SHA3_256, "54D18C9171D6354E90E38310715B2EF78CA482DACCDE7D0A9DE6AA8239D72809"},
                {LoremIpsumText.LOREM_IPSUM_PARAGRAPH, Type.SHA3_384, "5759AF6CE620B80B211BB412195D6633E1EB6E25A48B1DDAF1A1BC8EC975DC14AF275FC267B46666939862D54181AF0D"},
                {LoremIpsumText.LOREM_IPSUM_PARAGRAPH, Type.SHA3_512, "9FD9225C910289C414F71BE5FA03726D835D921AC339C36B2E54BE5F2CA5828129E2C034681EE59216A4B3BA0A3D17C9388236C1B6087E71B36FAA498EB86AEF"}
        });
    }

    private String input;
    private Type type;
    private String expectedHex;

    public Sha3TextTest(String input, Type type, String expectedHex) {
        this.input = input;
        this.type = type;
        this.expectedHex = expectedHex;
    }

    @Test
    public void testFullPathForTextSha3Algorithm() {
        // given
        Sha3 sha3 = new Sha3(type);
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

        // when
        byte[] encode = sha3.encode(inputBytes);

        // then
        Assert.assertEquals(expectedHex, HexTools.convertToHex(encode));
    }
}