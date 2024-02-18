package com.alabtaal.inventory.enums;

import java.util.ResourceBundle;

public enum FxmlView {

    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/login.fxml";
        }
    },
    MENU {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/menu.fxml";
        }
    },
    HOME {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/home.fxml";
        }
    },
   ITEM {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("item.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/item.fxml";
        }
    },

   SALE {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("sale.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/itemSale.fxml";
        }
    },

    PURCHASE {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("purchase.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/item.fxml";
        }
    },
    PAGE2 {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("page2.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/page2.fxml";
        }
    },
    PAGE3 {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("page3.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/page3.fxml";
        }
    },
    HELP {
        @Override
        public String getTitle() {
            return getStringFromRecourceBundle("help.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/help.fxml";
        }
    };

    static String getStringFromRecourceBundle(final String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

    public abstract String getTitle();

    public abstract String getFxmlFile();

}
