package be.li.mymeal.contracts;

/**
 * This simple interface allows defining callbacks.
 *
 * @param <TResult> The type of argument being passed to the callback.
 * @see be.li.mymeal.services.MealService for an example.
 */
public interface ICallback<TResult> {
    void Run(TResult result);
}
