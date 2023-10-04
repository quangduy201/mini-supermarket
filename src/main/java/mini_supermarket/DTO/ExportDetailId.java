package mini_supermarket.DTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ExportDetailId implements Serializable {
    public static final String EXPORT_NOTE = "exportNote";
    public static final String SHIPMENT = "shipment";

    @ManyToOne
    @JoinColumn(name = ExportNote.EXPORT_NOTE_ID)
    private ExportNote exportNote;
    @ManyToOne
    @JoinColumn(name = Shipment.SHIPMENT_ID)
    private Shipment shipment;

    public ExportDetailId() {
    }

    public ExportDetailId(ExportNote exportNote, Shipment shipment) {
        this.exportNote = exportNote;
        this.shipment = shipment;
    }

    public ExportNote getExport() {
        return exportNote;
    }

    public void setExport(ExportNote exportNote) {
        this.exportNote = exportNote;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ExportDetailId that)) return false;

        if (!getExport().equals(that.getExport())) return false;
        return getShipment().equals(that.getShipment());
    }

    @Override
    public int hashCode() {
        int result = getExport().hashCode();
        result = 31 * result + getShipment().hashCode();
        return result;
    }
}
