package tamagotchi;

public interface Control {
    void toFeed(Food food);

    void toClean();

    void toPlay(Play play);
    //Идея реализации: создать список ссылок с простенькими игрушками и меню, по которому при выборе игры, происходил переход на эти игры по ссылкам

    void toSleep();

    void toHeal();

    interface Food {

        Kind kind();

        Value value();

        enum Kind {
            SNACK, MEAL
        }

        record Value(int heals, int hunger, int weight, int happiness) {

        }
    }

    interface Play {
        record Empty() implements Play {

        }
    }
}
