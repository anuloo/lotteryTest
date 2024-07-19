# lotteryTest
A Kotlin app using MVVM and Jetpack Compose to display lottery draws from a RESTful API. Features include JSON parsing, detailed draw views, random lottery tickets, navigation, offline caching, and enhanced UI/UX with animations and custom components.


# 🎲 Lottery Draws App

Welcome to the **Lottery Draws App**! This project is a test for a company utilizing the **MVVM framework** and **Kotlin Jetpack Compose** to load and display a RESTful API within the app. Dive into a world of lottery draws with a sleek interface and interactive features!

## 🚀 Features

### 1.3 Essential Tasks

1. **Parse JSON Data**: Seamlessly load and parse the provided JSON data within the app.
2. **Display Lottery Draws**: A simple yet elegant view listing all lottery draws along with their respective draw dates.
3. **Unit and Integration Testing**: I ve done just 1 due to i had issues with the HiltTest

### 1.4 Additional Tasks

1. **Detail View for Each Draw**: Dive deeper into each lottery draw with a detailed view showcasing all numbers and the bonus ball.
2. **Lottery Tickets**: This is added just later stage see screenshot
3. **Navigation**: Smooth and intuitive navigation from the main list view to the detailed views of each draw.
4. **Additional Tests**: Ive added some unit test not too many unfortunatelly.
5. **Local Storage**: Cache the lottery draws locally to enable offline viewing.
6. **UI/UX Enhancements**: Boost the visual appeal and user experience with loading animations and custom UI components.

## 🛠 Technologies Used

- **Kotlin**: The primary programming language.
- **Jetpack Compose**: For creating a modern, responsive UI.
- **MVVM Framework**: For a clean and efficient architecture.
- **RESTful API**: For fetching and displaying the latest lottery draws. again i actually done a mock api server to imitate the proper behavior of the restful API
- **Local Storage**: For caching data and offline access. I ve used room database so it has a Flow<List> this way we can seamlessly update the UI if the state has changed

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

