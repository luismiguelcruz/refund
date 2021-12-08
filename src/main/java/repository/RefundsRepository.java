package repository;

import com.google.common.collect.ImmutableList;
import model.Refund;

import java.util.Collections;
import java.util.List;

public class RefundsRepository {

    public List<Refund> getRefunds(final int idRefund) {
        return ImmutableList.of(new Refund(1, 1, Collections.emptyList()));
    }

    public Refund createRefund(final Refund refund) {
        return refund;
    }
}
