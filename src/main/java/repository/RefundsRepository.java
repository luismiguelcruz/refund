package repository;

import com.google.common.collect.ImmutableList;
import model.Refund;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RefundsRepository {

    public List<Refund> getRefunds(final int idOrder) {
        return ImmutableList.of(new Refund(1, idOrder, Collections.emptyList()));
    }

    public Optional<Refund> getRefund(final int idRefund) {
        return Optional.of(new Refund(idRefund, 1, Collections.emptyList()));
    }

    public boolean exists(final int idRefund) {
        return true;
    }

    public Refund createRefund(final Refund refund) {
        return refund;
    }

    public void removeRefund(final int idRefund) {
    }
}
