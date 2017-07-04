package seedu.whatsnext.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.whatsnext.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.whatsnext.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.whatsnext.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.whatsnext.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.whatsnext.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.whatsnext.logic.commands.exceptions.CommandException;
import seedu.whatsnext.model.person.BaseTask;
import seedu.whatsnext.model.person.exceptions.DuplicatePersonException;
import seedu.whatsnext.model.person.Floating;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Floating toAdd;

    /**
     * Creates an AddCommand to add the specified {@code ReadOnlyPerson}
     */
    public AddCommand(BaseTask task) {
        if(task.getType().equals("floating"))
            toAdd = new Floating(task);
        else if (task.getType().equals("deadline"))
            toAdd = new Deadline(task);
        else if (task.getType().equals("event"))
            toAdd = new Event(task);
    }
s
    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicatePersonException e) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

    }

}
