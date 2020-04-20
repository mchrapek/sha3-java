package pl.marek.sha3.operations;

import pl.marek.sha3.Config;
import pl.marek.sha3.transformation.*;

public class Permute {

    private final Theta theta;
    private final Iota iota;
    private final Chi chi;
    private final Pi pi;
    private final Rho rho;

    public Permute() {
        theta = new Theta();
        iota = new Iota();
        chi = new Chi();
        pi = new Pi();
        rho = new Rho();
    }

    public void permute(final long[][] lane) {
        for (int i = 0; i < Config.ROUNDS; i++) {
            theta.transform(lane);
            rho.transform(lane);
            pi.transform(lane);
            chi.transform(lane);
            iota.transform(lane, i);
        }
    }
}
