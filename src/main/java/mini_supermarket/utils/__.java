package mini_supermarket.utils;

public class __ {
    public static class ACCOUNT {
        public static final String ACCOUNT = "account";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String STAFF = "staff";
        public static final String LAST_SIGNED_IN = "last_signed_in";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "account_id";
            public static final String USERNAME = "account_username";
            public static final String PASSWORD = "account_password";
            public static final String LAST_SIGNED_IN = "account_last_signed_in";
            public static final String DELETED = "account_deleted";
        }
    }

    public static class BRAND {
        public static final String BRAND = "brand";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String SUPPLIER = "supplier";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "brand_id";
            public static final String NAME = "brand_name";
            public static final String SUPPLIER = "brand_supplier";
        }
    }

    public static class CATEGORY {
        public static final String CATEGORY = "category";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String QUANTITY = "quantity";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "category_id";
            public static final String NAME = "category_name";
            public static final String QUANTITY = "category_quantity";
            public static final String DELETED = "category_deleted";
        }
    }

    public static class CUSTOMER {
        public static final String CUSTOMER = "customer";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String BIRTHDATE = "birthdate";
        public static final String PHONE = "phone";
        public static final String MEMBERSHIP = "membership";
        public static final String SIGNED_UP_DATE = "signed_up_date";
        public static final String POINT = "point";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "customer_id";
            public static final String NAME = "customer_name";
            public static final String GENDER = "customer_gender";
            public static final String BIRTHDATE = "customer_birthdate";
            public static final String PHONE = "customer_phone";
            public static final String MEMBERSHIP = "customer_membership";
            public static final String SIGNED_UP_DATE = "customer_signed_up_date";
            public static final String POINT = "customer_point";
            public static final String DELETED = "customer_deleted";
        }
    }

    public static class DECENTRALIZATION {
        public static final String DECENTRALIZATION = "decentralization";
        public static final String ROLE = "id.role";
        public static final String MODULE = "id.module";
        public static final String FUNCTION = "id.function";

        public static class COLUMN {

        }
    }

    public static class DISCOUNT {
        public static final String DISCOUNT = "discount";
        public static final String ID = "id";
        public static final String PERCENT = "percent";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String STATUS = "status";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "discount_id";
            public static final String PERCENT = "discount_percent";
            public static final String START_DATE = "discount_start_date";
            public static final String END_DATE = "discount_end_date";
            public static final String STATUS = "discount_status";
            public static final String DELETED = "discount_deleted";
        }
    }

    public static class DISCOUNT_DETAIL {
        public static final String DISCOUNT_DETAIL = "discount_detail";
        public static final String DISCOUNT = "id.discount";
        public static final String PRODUCT = "id.product";
        public static final String STATUS = "status";

        public static class COLUMN {
            public static final String STATUS = "discount_detail_status";
        }
    }

    public static class EXPORT_DETAIL {
        public static final String EXPORT_DETAIL = "export_detail";
        public static final String EXPORT_NOTE = "id.exportNote";
        public static final String SHIPMENT = "id.shipment";
        public static final String QUANTITY = "quantity";
        public static final String TOTAL = "total";

        public static class COLUMN {
            public static final String QUANTITY = "export_detail_quantity";
            public static final String TOTAL = "export_detail_total";
        }
    }

    public static class EXPORT_NOTE {
        public static final String EXPORT_NOTE = "export_note";
        public static final String ID = "id";
        public static final String STAFF = "staff";
        public static final String INVOICE_DATE = "invoice_date";
        public static final String TOTAL = "total";
        public static final String REASON = "reason";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "export_note_id";
            public static final String INVOICE_DATE = "export_note_invoice_date";
            public static final String TOTAL = "export_note_total";
            public static final String REASON = "export_note_reason";
            public static final String DELETED = "export_note_deleted";
        }
    }

    public static class FUNCTION {
        public static final String FUNCTION = "function";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "function_id";
            public static final String NAME = "function_name";
            public static final String DELETED = "function_deleted";
        }
    }

    public static class IMPORT_NOTE {
        public static final String IMPORT_NOTE = "import_note";
        public static final String ID = "id";
        public static final String STAFF = "staff";
        public static final String RECEIVED_DATE = "received_date";
        public static final String TOTAL = "total";
        public static final String SUPPLIER = "supplier";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "import_note_id";
            public static final String RECEIVED_DATE = "import_note_received_date";
            public static final String TOTAL = "import_note_total";
            public static final String DELETED = "import_note_deleted";
        }
    }

    public static class MODULE {
        public static final String MODULE = "module";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "module_id";
            public static final String NAME = "module_name";
            public static final String DELETED = "module_deleted";
        }
    }

    public static class PRODUCT {
        public static final String PRODUCT = "product";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String BRAND = "brand";
        public static final String CATEGORY = "category";
        public static final String UNIT = "unit";
        public static final String COST = "cost";
        public static final String QUANTITY = "quantity";
        public static final String IMAGE = "image";
        public static final String BARCODE = "barcode";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "product_id";
            public static final String NAME = "product_name";
            public static final String UNIT = "product_unit";
            public static final String COST = "product_cost";
            public static final String QUANTITY = "product_quantity";
            public static final String IMAGE = "product_image";
            public static final String BARCODE = "product_barcode";
            public static final String DELETED = "product_deleted";
        }
    }

    public static class PROMOTION {
        public static final String PROMOTION = "promotion";
        public static final String ID = "id";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String STATUS = "status";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "promotion_id";
            public static final String START_DATE = "promotion_start_date";
            public static final String END_DATE = "promotion_end_date";
            public static final String STATUS = "promotion_status";
            public static final String DELETED = "promotion_deleted";
        }
    }

    public static class PROMOTION_GIFT {
        public static final String PROMOTION_GIFT = "promotion_gift";
        public static final String PROMOTION = "id.promotion";
        public static final String PRODUCT = "id.product";
        public static final String QUANTITY = "quantity";

        public static class COLUMN {
            public static final String QUANTITY = "promotion_gift_quantity";
        }
    }

    public static class PROMOTION_ITEM {
        public static final String PROMOTION_ITEM = "promotion_item";
        public static final String PROMOTION = "id.promotion";
        public static final String PRODUCT = "id.product";
        public static final String QUANTITY = "quantity";

        public static class COLUMN {
            public static final String QUANTITY = "promotion_item_quantity";
        }
    }

    public static class RECEIPT {
        public static final String RECEIPT = "receipt";
        public static final String ID = "id";
        public static final String STAFF = "staff";
        public static final String CUSTOMER = "customer";
        public static final String INVOICE_DATE = "invoice_date";
        public static final String TOTAL = "total";
        public static final String RECEIVED = "received";
        public static final String EXCESS = "excess";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "receipt_id";
            public static final String INVOICE_DATE = "receipt_invoice_date";
            public static final String TOTAL = "receipt_total";
            public static final String RECEIVED = "receipt_received";
            public static final String EXCESS = "receipt_excess";
            public static final String DELETED = "receipt_deleted";

        }
    }

    public static class RECEIPT_DETAIL {
        public static final String RECEIPT_DETAIL = "receipt_detail";
        public static final String RECEIPT = "id.receipt";
        public static final String PRODUCT = "id.product";
        public static final String QUANTITY = "quantity";
        public static final String TOTAL = "total";
        public static final String PERCENT = "percent";

        public static class COLUMN {
            public static final String QUANTITY = "receipt_detail_quantity";
            public static final String TOTAL = "receipt_detail_total";
            public static final String PERCENT = "receipt_detail_percent";
        }
    }

    public static class ROLE {
        public static final String ROLE = "role";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "role_id";
            public static final String NAME = "role_name";
            public static final String DELETED = "role_deleted";
        }
    }

    public static class SHIPMENT {
        public static final String SHIPMENT = "shipment";
        public static final String ID = "id";
        public static final String PRODUCT = "product";
        public static final String UNIT_PRICE = "unit_price";
        public static final String QUANTITY = "quantity";
        public static final String REMAIN = "remain";
        public static final String MFG = "mfg";
        public static final String EXP = "exp";
        public static final String SKU = "sku";
        public static final String IMPORT_NOTE = "importNote";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "shipment_id";
            public static final String UNIT_PRICE = "shipment_unit_price";
            public static final String QUANTITY = "shipment_quantity";
            public static final String REMAIN = "shipment_remain";
            public static final String MFG = "shipment_mfg";
            public static final String EXP = "shipment_exp";
            public static final String SKU = "shipment_sku";
            public static final String DELETED = "shipment_deleted";
        }
    }

    public static class STAFF {
        public static final String STAFF = "staff";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String BIRTHDATE = "birthdate";
        public static final String PHONE = "phone";
        public static final String ADDRESS = "address";
        public static final String EMAIL = "email";
        public static final String ENTRY_DATE = "entry_date";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "staff_id";
            public static final String NAME = "staff_name";
            public static final String GENDER = "staff_gender";
            public static final String BIRTHDATE = "staff_birthdate";
            public static final String PHONE = "staff_phone";
            public static final String ADDRESS = "staff_address";
            public static final String EMAIL = "staff_email";
            public static final String ENTRY_DATE = "staff_entry_date";
            public static final String DELETED = "staff_deleted";
        }
    }

    public static class STATISTIC {
        public static final String STATISTIC = "statistic";
        public static final String ID = "id";
        public static final String DATE = "date";
        public static final String AMOUNT = "amount";
        public static final String EXPENSES = "expenses";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "statistic_id";
            public static final String DATE = "statistic_date";
            public static final String AMOUNT = "statistic_amount";
            public static final String EXPENSES = "statistic_expenses";
            public static final String DELETED = "statistic_deleted";
        }
    }

    public static class SUPPLIER {
        public static final String SUPPLIER = "supplier";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String ADDRESS = "address";
        public static final String EMAIL = "email";
        public static final String DELETED = "deleted";

        public static class COLUMN {
            public static final String ID = "supplier_id";
            public static final String NAME = "supplier_name";
            public static final String PHONE = "supplier_phone";
            public static final String ADDRESS = "supplier_address";
            public static final String EMAIL = "supplier_email";
            public static final String DELETED = "supplier_deleted";
        }
    }
}
