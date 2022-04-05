package pl.mac.bry.order;

enum OrderStatus {
    REALIZED("Order is realized"),
    UNREALIZED("Order is unrealized");

        private final String description;

        OrderStatus(String description) {
            this.description = description;
        }

        String getDescription() {
            return description;
        }

        static OrderStatus valuesOfDescription(String description) {
            for (OrderStatus orderStatus :  values()) {
                if(orderStatus.getDescription().equals(description)){
                    return orderStatus;
                }
            }
            return null;
        }
    }
