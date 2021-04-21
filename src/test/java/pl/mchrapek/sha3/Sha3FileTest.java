package pl.mchrapek.sha3;

import org.junit.Assert;
import org.junit.Test;
import pl.mchrapek.sha3.utils.HexTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sha3FileTest {

    private final String expectedHashPdf =
            "C061CDC85F8D1D6A57F974F4364A5854C049A33BACB5A0A6F26CEA15D053475593ABED0998243E9763E85CE3E563F0B857A9746B0E177750EDF3E3C9A6DE663A";

    @Test
    public void testFullPathForFileSha3Algorithm() throws IOException {
        // given
        Sha3 sha3 = new Sha3(Type.SHA3_512);
        File file = new File("src/test/resources/dummy.pdf");
        InputStream inputStream = new FileInputStream(file);

        // when
        byte[] encode = sha3.encode(inputStream);

        // then
        Assert.assertEquals(expectedHashPdf, HexTools.convertToHex(encode));
    }
}
