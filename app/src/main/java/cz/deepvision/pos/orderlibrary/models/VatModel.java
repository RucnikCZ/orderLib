package cz.deepvision.pos.orderlibrary.models;

import androidx.annotation.Nullable;

import java.util.Objects;

public class VatModel {
    private Double vatPricing;
    private Double baseDPH;
    private Double dphRest;
    private Double sum;

    public VatModel() {
        vatPricing = 0.0;
        baseDPH = 0.0;
        dphRest = 0.0;
        sum = 0.0;
    }

    public VatModel(Double vatPricing) {
        this.vatPricing = vatPricing;
        baseDPH = 0.0;
        dphRest = 0.0;
        sum = 0.0;
    }

    public Double getVatPricing() {
        return vatPricing;
    }

    public void setVatPricing(Double vatPricing) {
        this.vatPricing = vatPricing;
    }

    public Double getBaseDPH() {
        return baseDPH;
    }

    public void setBaseDPH(Double baseDPH) {
        this.baseDPH = baseDPH;
    }

    public Double getDphRest() {
        return dphRest;
    }

    public void setDphRest(Double dphRest) {
        this.dphRest = dphRest;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vatPricing, baseDPH, dphRest, sum);

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof VatModel)) {
            return false;
        }
        VatModel vatModel = (VatModel) obj;
        return vatModel.getVatPricing().equals(vatPricing);
    }
}
