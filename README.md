# 📱 MiniEcommerce

MiniEcommerce is a modern Android application built with Jetpack Compose, following a clean, multi-module architecture. It demonstrates best practices for building scalable, maintainable, and responsive UI screens for login, registration, and other features.

# 🚀 Features

- **🔐 Authentication:** Login and registration screens with real-time validation.
- **🏠 Home Screen:** Displays a list of products fetched from a remote API.
- **🛒 Shopping Cart:** Users can add products to a local cart, which is persisted using Room.
- **👤 User Profile:** A placeholder screen for future user profile functionality.
- **🎨 Jetpack Compose UI:** The entire UI is built with Jetpack Compose, with no XML layouts.
- **📦 Modular Architecture:** A clean, multi-module architecture that separates concerns between the UI, domain, and data layers.
- **🌓 Dark Mode Support:** The app is fully theme-reactive and adapts to the system's light or dark mode setting.
- **🌈 Shared Theme:** A centralized Material 3 theme in the `:core` module.
- **🧠 State Management:** Using `ViewModel` and `StateFlow` for a reactive UI.
- **⚙️ Validation:** Real-time form validation for email and password fields.
- **👁️ Show/Hide Password:** A toggle to show or hide the password in input fields.
- **✈️ Offline Handling:** A Lottie-powered screen for no-internet scenarios.

# ✨ Recent Changes

- **🛒 Cart Feature:** Added a new `feature-cart` module, which includes a `CartScreen`, `CartViewModel`, and a local database using Room to persist cart items.
- **👤 Profile Feature:** Added a new `feature-profile` module with a placeholder `ProfileScreen` and `ProfileViewModel`.
- **🏠 Home Screen Refactor:** The home screen has been updated with a `HomeViewModel` to manage its UI state, and the UI has been separated into `HomeScreen`, `HomeContent`, and `CategoryScreen`.
- **Navigation:** Added a `BottomNavigationBar` and `BottomNavItem` to the core UI components, and updated the navigation in `AppNavHost` and `Routes`.
- **📦 New Data and Domain Layers:** Added new repositories (`ProductRepository`, `UserRepository`) and use cases (`GetProductsUseCase`), along with a `Product` model.

# 🏗️ Project Structure

```
MiniEcommerce/
│
├── app/                 # Main application module (DI, entry point)
├── core/                # Shared utilities, navigation, and base UI components
├── data/                # Data sources and repository implementations
├── domain/              # Core data models and repository interfaces
├── feature-auth/        # Self-contained authentication feature
├── feature-cart/        # Self-contained cart feature
├── feature-home/        # Self-contained home screen feature
└── feature-profile/     # Self-contained profile feature
```

# 💡 Tech Stack

| Category | Technology |
| --- | --- |
| **Language** | Kotlin |
| **UI Toolkit** | Jetpack Compose |
| **Architecture** | MVVM + Clean Architecture (Multi-Module) |
| **Navigation** | Jetpack Navigation-Compose |
| **Dependency Injection** | Koin |
| **Async / Reactive** | Kotlin Coroutines + StateFlow |
| **Backend** | Firebase Auth, Firebase Firestore |
| **Local Storage** | Room |
| **Crash Reporting**| Firebase Crashlytics |
| **Animations** | Lottie |

# 🧩 Setup Instructions

1️⃣ **Clone the repository**

```bash
git clone https://github.com/yourusername/MiniEcommerce.git
cd MiniEcommerce
```

2️⃣ **Open in Android Studio**

-   Use Android Studio Hedgehog or later.
-   Ensure you have a `google-services.json` file from your Firebase project placed in the `app/` directory.

3️⃣ **Build the project**

```bash
./gradlew assembleDebug
```

# 🪪 License

Copyright (c) 2025 Abdul Hanan Khan

Licensed under the MIT License. You may use, copy, modify, and distribute this software for any purpose, provided that the above copyright notice and this permission notice are included in all copies or substantial portions of the Software.

# 💬 Contact

Author: Abdul Hanan Khan
📧 Email: ahkhan622@gmail.com
🌐 GitHub: @Ahkh56
