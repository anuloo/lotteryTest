# Lottery App

## Overview

The Lottery App is a modern Android application built using Jetpack Compose, Hilt for dependency injection, and Kotlin Coroutines. The app provides a user-friendly interface for viewing lottery draws, checking results, and generating QR codes for lottery tickets.

## Features

- **Lottery List Screen**: Displays a list of recent lottery draws with their details.
- **Lottery Detail Screen**: Shows detailed information about a specific lottery draw, including numbers and prize details.
- **Check Draw Screen**: Allows users to generate lottery numbers, view them in a formatted ticket view, and check results.

## Technologies Used

- **Jetpack Compose**: Utilized for building a reactive and modern UI in a declarative manner. Compose simplifies the UI development process and integrates well with the MVVM architecture.
- **Hilt**: Used for dependency injection, providing a scalable and manageable way to handle dependencies across the application.
- **Kotlin Coroutines**: Employed for managing asynchronous operations and background tasks efficiently, ensuring smooth and responsive UI interactions.
- **Ktor**: Used for handling network requests. If included in the project, it would be for making API calls to fetch lottery data.

## Architecture

The application follows the **MVVM (Model-View-ViewModel)** architecture pattern:

- **Model**: Represents the data layer. It includes domain models and use cases that interact with data sources.
- **View**: Composed of Jetpack Compose UI components. It observes state changes from the ViewModel and renders the UI accordingly.
- **ViewModel**: Manages UI-related data and business logic. It fetches data from use cases and provides it to the View.

## Key Components

- **Screens**: Implemented as Composables to manage different views of the application, such as the Lottery List, Lottery Detail, and Check Draw screens.
- **ViewModels**: Handle business logic and state management. They interact with use cases and update the UI based on user interactions or data changes.
- **Use Cases**: Encapsulate business logic and data manipulation. They are called by ViewModels to perform specific operations like fetching lottery data or generating QR codes.
- **Components**: Reusable UI elements like BallItem, LotteryItem, and TicketView enhance modularity and maintainability.

## Improvements

1. **Localization**: The app currently lacks localization support. Implementing string resources for multiple languages would make the app more accessible to a global audience.
2. **Error Handling**: Enhancing error handling and user feedback could improve the app’s resilience and user experience.
3. **UI Testing**: While unit tests for ViewModels, use cases, and DAOs have been completed, UI testing remains to ensure the end-to-end functionality and user interface behavior.

## Conclusion

This Lottery App showcases a modern approach to Android development using Jetpack Compose for UI, Hilt for dependency injection, and Kotlin Coroutines for asynchronous tasks. It demonstrates effective use of MVVM architecture and Compose's powerful features for creating a responsive and user-friendly application.

## 🧪 Testing

- **Unit Tests**: Ive validated the smallest parts of the application. mainly the uscases and the repositories
- **Integration Tests**: just done the DAO test just for fun Like i said i had an issue with the libaries.

## 📲 How to Use

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/anuloo/lotteryTest.git
    ```
2. **Open in Android Studio**: Open the project in Android Studio to start exploring the code.
3. **Build and Run**: Build the project and run it on an emulator or physical device to see it in action.

## 📸 Screenshots

![Screenshot_20240718_063328](https://github.com/user-attachments/assets/edf02dcb-28d9-4028-9d55-2e6093b67952)
![Screenshot_20240718_063555](https://github.com/user-attachments/assets/94b8df03-b613-4a20-a5a1-94f3b88b1779)
![Screenshot_20240719_025737](https://github.com/user-attachments/assets/767c5d2a-5191-4719-b23f-6307371b4369)



## 🤝 Contributing

We welcome contributions! Please fork the repository and create a pull request with your improvements.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Enjoy the app and may the odds be ever in your favor! 🍀

