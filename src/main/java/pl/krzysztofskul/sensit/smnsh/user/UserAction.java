package pl.krzysztofskul.sensit.smnsh.user;

/*
 * TODO 2023-06-24 create user login and register functionality
 */
public enum UserAction {
        REGISTER {
            @Override
            public String toString() {
                return "REGISTERED";
            }
        },
        LOG_IN {
            @Override
            public String toString() {
                return "LOGGED IN";
            }
        },
        PROJECT_CREATE {
            @Override
            public String toString() {
                return "CREATED NEW PROJECT";
            }
            public String toStringEN() {
                return "New project created";
            }
            public String toStringPL() {
                return "Utworzono nowy projekt";
            }
        },
        PROJECT_UPDATE {
            @Override
            public String toString() {
                return "PROJECT UPDATED";
            }
            public String toStringEN() {
                return "Project updated";
            }
            public String toStringPL() {
                return "Zaktualizowano projekt";
            }
        },
        PROJECT_DELETE {
            @Override
            public String toString() {
                return "DELETED PROJECT";
            }
            public String toStringEN() {
                return "Project deleted";
            }
            public String toStringPL() {
                return "Usunięto projekt";
            }
        },
        DEMO_MODE_START {
            @Override
            public String toString() {
                return "DEMO MODE START";
            }
        },
        DEMO_MODE_STOP {
            @Override
            public String toString() {
                return "DEMO MODE STOP";
            }
        }
    }