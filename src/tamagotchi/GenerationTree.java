package tamagotchi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GenerationTree {
    private  final Map<Function<Tamagotchi, Boolean>, GenerationTree> child = new HashMap<>();

}
