<h1>Clean Architecture Structure</h1>

<p>This document outlines the clean architecture structure used in this project, explaining the purpose and organization of each layer and package.</p>

<h2>1. Data Layer</h2>
<p>The data layer is responsible for managing data operations. It includes local and remote data sources, data mappers, and the repository implementation.</p>

<h3>1.1 Local</h3>
<p>This package contains classes and objects for handling data stored locally on the device, such as:</p>
<ul>
    <li>Room Database</li>
    <li>SharedPreferences</li>
    <li>Files or other storage mechanisms</li>
</ul>

<h3>1.2 Mapper</h3>
<p>Mappers are used to convert data models from the data layer (e.g., database or network models) into domain models used in the business logic layer.</p>

<h3>1.3 Remote</h3>
<p>This package handles data retrieved from remote sources such as APIs. It includes network calls and the handling of requests and responses.</p>

<h4>1.3.1 Request</h4>
<p>Defines data structures and configurations for API requests.</p>

<h4>1.3.2 Response</h4>
<p>Holds data structures for responses received from the API. These models are typically mapped to domain models using mappers.</p>

<h3>1.4 Repository</h3>
<p>The repository serves as a single source of truth, combining data from local and remote sources as needed. It implements the domain layer's repository interfaces.</p>

<hr>

<h2>2. Dependency Injection (di)</h2>
<p>This package includes all classes and modules required for Dependency Injection (DI). DI frameworks like Dagger Hilt are used to provide dependencies to various parts of the application.</p>

<hr>

<h2>3. Domain Layer</h2>
<p>The domain layer represents the core business logic of the application. It is independent of any framework or external library.</p>

<h3>3.1 Model</h3>
<p>Defines the business entities (domain models) used throughout the domain layer.</p>

<h3>3.2 Repository</h3>
<p>Contains the repository interfaces, which are implemented by the data layer. This ensures the domain layer is decoupled from data layer implementations.</p>

<h3>3.3 UseCase</h3>
<p>Use cases encapsulate specific business rules or operations. They interact with the repository interfaces to execute application-specific tasks.</p>

<hr>

<h2>4. Presentation Layer</h2>
<p>The presentation layer is responsible for displaying data to the user and handling user interactions. It includes UI-related components and ViewModels.</p>

<h3>4.1 Activity</h3>
<p>Activities serve as entry points to the UI and typically host fragments. They are responsible for setting up the navigation and other high-level tasks.</p>

<h3>4.2 Adapter</h3>
<p>Adapters are used with RecyclerViews or other UI components to bind data to the view.</p>

<h3>4.3 Base</h3>
<p>Contains base classes for common components like BaseActivity, BaseFragment, or BaseViewModel, which provide shared functionality.</p>

<h3>4.4 Fragment</h3>
<p>Fragments represent a portion of the UI. They work closely with ViewModels to observe and display data.</p>

<h3>4.5 ViewModel</h3>
<p>ViewModels store and manage UI-related data. They interact with the domain layer and expose LiveData or StateFlow for the UI to observe.</p>

<hr>

<h2>5. Utility</h2>
<p>This package includes helper classes, constants, and extensions used throughout the project.</p>

<h3>5.1 Constant</h3>
<p>Defines constant values used across the application, such as API keys, endpoints, or configuration values.</p>

<h3>5.2 Enums</h3>
<p>Holds enumerations (enums) that define fixed sets of related constants, improving type safety.</p>

<h3>5.3 Extension</h3>
<p>Includes extension functions to add utility methods or simplify code readability.</p>
