package parser;

import model.schedule.SchedulingPeriod;

/**
 * Class to parse an Text scheduling instance.
 */
@SuppressWarnings("unused")
public class TextParser implements IParser {
    @Override
    public SchedulingPeriod loadFile(String path) {
        throw new RuntimeException("TextParser not implemented yet.");
    }
}
