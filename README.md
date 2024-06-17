# Baubap Challenge

The project was created using Clean Architecture based on Domain Driven Desgin concepts and MVVM in the presentation layer taking advantage of AndroidX architecture components.

## Libraries
- UI: Created using Jetpack compose
- DI: Structured using Hilt
- Architecture: Clean Architecture based on Domian Driven Design concepts
- Presentation architecture: Using Android's frovided MVVM lifecycle aware components, Flows to emit and collect events/states and Coroutines to handle async calls
- Networking: Not implemented (the api client impl is returning a delayed DTO)
- Unit testing: Mockito and Turbine

## Considerations
- The domian was structure in "by-feature" packages, while the project itself using the Clean Architecture mostly used packages (data, domain, view, etc.)
- Though the use-case approach was used, I created a AuthenticationProviderUseCase interface used to specify the available operations (login, forget password, sign up, etc); so that
if in the future another authentication provider is added (facebook, google, paypal, etc) in the same view, and therefore using the same view model; instead of injecting the concrete usecase (like know), the AuthenticationProviderManager can be injected and the use case can dynamically change without modifying the underlying layers. In this approach, each concrete provider will provide an implementation of the specified operations including the Object used to execute tasks (abstract class LoginCredentials | UsernamePasswordCredentials).
- There were some considerations that were left outside of the scope of this project (navigation, UI related stuff, integration of data sources like database, local storage, or the implementation of api clients, api client specification including network error handling, interceptors, caching mechanisms, syncing, token management, etc.) 
