package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ExpenseService.Expense.ExpenseType.*;

class ExpenseServiceTest {

    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL, "Project A");
        ExpenseService expenseService = new ExpenseService();
        // when
        ExpenseType type = expenseService.getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(INTERNAL_PROJECT_EXPENSE, type);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project A");
        ExpenseService expenseService = new ExpenseService();
        // when
        ExpenseType type = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(EXPENSE_TYPE_A, type);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project B");
        ExpenseService expenseService = new ExpenseService();
        // when
        ExpenseType type = expenseService.getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(EXPENSE_TYPE_B, type);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project C");
        ExpenseService expenseService = new ExpenseService();
        // when
        ExpenseType type = expenseService.getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(OTHER_EXPENSE, type);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Project invalid");
        ExpenseService expenseService = new ExpenseService();
        // when
        UnexpectedProjectTypeException unexpectedProjectTypeException = Assertions.assertThrows(UnexpectedProjectTypeException.class,
                () -> { expenseService.getExpenseCodeByProjectTypeAndName(project);} );
        // then
        Assertions.assertEquals("You enter invalid project type", unexpectedProjectTypeException.getMessage());
    }
}