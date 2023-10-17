package mini_supermarket.DTO;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import mini_supermarket.utils.__;

import java.io.Serializable;

@Embeddable
public class ExportDetailId implements Serializable {
    @ManyToOne
    @JoinColumn(name = __.EXPORT_NOTE.COLUMN.ID)
    private ExportNote exportNote;
    @ManyToOne
    @JoinColumn(name = __.SHIPMENT.COLUMN.ID)
    private Shipment shipment;

    public ExportDetailId() {
    }

    public ExportDetailId(ExportNote exportNote, Shipment shipment) {
        this.exportNote = exportNote;
        this.shipment = shipment;
    }

    public ExportNote getExportNote() {
        return exportNote;
    }

    public void setExportNote(ExportNote exportNote) {
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

        if (!getExportNote().equals(that.getExportNote())) return false;
        return getShipment().equals(that.getShipment());
    }

    @Override
    public int hashCode() {
        int result = getExportNote().hashCode();
        result = 31 * result + getShipment().hashCode();
        return result;
    }
}
