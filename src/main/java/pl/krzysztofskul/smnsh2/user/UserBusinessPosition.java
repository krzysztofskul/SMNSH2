package pl.krzysztofskul.smnsh2.user;

public enum UserBusinessPosition {
    ADMIN {
        @Override
        public String toString() {
            return "ADMIN";
        }
        public String toStringPL() {
            return "Administrator";
        }
        public String toStringEN() {
            return "Admin";
        }
    },
    DESIGNER {
        @Override
        public String toString() {
            return "DESIGNER";
        }
        public String toStringPL() {
            return "Projektant";
        }
        public String toStringEN() {
            return "Designer";
        }
    },
    PROJECT_MANAGER {
        @Override
        public String toString() {
            return "PROJECT MANAGER";
        }
        public String toStringPL() {
            return "Kierownik projektu";
        }
        public String toStringEN() {
            return "Project manager";
        }
    },
    SALES_REP {
        @Override
        public String toString() {
            return "SALES REP.";
        }
        public String toStringPL() {
            return "Przedstawiciel handlowy";
        }
        public String toStringEN() {
            return "Sales rep.";
        }
    }
}