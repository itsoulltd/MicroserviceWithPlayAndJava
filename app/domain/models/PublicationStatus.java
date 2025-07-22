package domain.models;

public enum PublicationStatus {
    Approved("approved"), Pending("pending"), Rejected("rejected"), None("none");

    private String value;

    PublicationStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static PublicationStatus fromValue(String value) {
        PublicationStatus status;
        switch (value){
            case "approved":
                status = Approved;
                break;
            case "pending":
                status = Pending;
                break;
            case "rejected":
                status = Rejected;
                break;
            default:
                status = None;
        }
        return status;
    }
}
