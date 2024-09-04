# FoodCart - Spring Boot Project

#### FoodCart is a cutting-edge online food delivery platform designed to bridge the gap between passionate food enthusiasts and diverse dining experiences. Whether you’re a restaurant owner looking to expand your reach or a foodie searching for the perfect meal, FoodCart offers a seamless, intuitive, and secure environment for everyone. With its robust multi-vendor system, tailored admin panels, and user-friendly features, FoodCart redefines the online dining experience, making it not just a service, but a community.

## Live Demo | API Documentation(Swagger)
http://foodcart-env.ap-south-1.elasticbeanstalk.com/swagger-ui/index.html

# Key Features:

  + **Multi-vendor Support:** A diverse marketplace where multiple restaurants can register and showcase their menus, offering users a wide range of dining options.

+ **Tailored Admin Panels:** Restaurant owners get a specialized admin panel to manage their listings, orders, and events, while the platform owner benefits from a comprehensive super admin panel.
+ **Role-based Access Control:** Securely manage different user roles **(admin, restaurant owner, customer)** ensuring a controlled and safe environment.
+ **User-friendly Functions:** Enhance user experience with features like ‘**Add to Favorite**,’ ‘**Add to Cart**,’ ‘**Remove from Cart**,’ and more.
+ **Secure Transactions:** Integrated with **Stripe** for secure payment processing, along with strong authentication and password management practices.
+ **Event Notifications:** Restaurants can create and promote events, with customers receiving timely email notification.
+ **Review System:** Empowering both restaurant owners and customers to provide and view ratings and feedback.

## Security Features:
+ **Authentication:** JWT tokens are used for secure authentication and session management.
+ **Authorization:** Role-based access control is implemented to manage permissions, ensuring that only authorized users can perform specific actions.
+ **Data Encryption:** Sensitive data, including passwords and payment details, is encrypted to protect user information from unauthorized access.


# Planned Future Enhancements:
+ **Password Reset via Email:** Implementing a secure and convenient method for users to reset their passwords through email.
+ **UI/UX Improvements:** Introducing a responsive and visually appealing frontend interface to complement the backend functionalities.
+ **Event Feature Enhancements:** Expanding the event creation and management capabilities, allowing restaurants to offer more dynamic and interactive experiences. Future updates will include more detailed event descriptions, customizable notifications, and user engagement tracking.
+ **Review System Enhancements:** Improving the review and rating system to include more granular feedback options, verified reviews, and sentiment analysis. This will provide more accurate and meaningful insights for both restaurants and customers.


# Technologies Used:
+ **Back-End:** Spring Boot, Spring Security, Spring Data JPA
+ **Database:** MySQL
+ **Payment Gateway:**: Stripe
+ **Authentication:** JWT, Spring Security
+ **Deployment:** AWS
+ **Notifications:** Email notifications for events and order updates



# Why I Created This Project:
FoodCart reflects my passion for coding and my commitment to developing practical solutions that can have a positive impact on users' daily lives. It’s a project that merges my technical skills with my enthusiasm for solving real-world problems in the food industry.

# Getting Started:
## Prerequisites:
- **Java 17+**
- **MySQL**
- **Maven**
- **AWS Account**


## Installation and Setup

1. **Clone the repository to your local computer.**

```bash
  git clone https://github.com/avijit18/FoodCart

```

2. **Navigate to the Project Directory**

```bash
  cd foodcart
```

3. **Set Up Stripe API**:
 + Login to **Stripe** offical website or Sign-up for new account.
 + Once logged in, navigate to the **Dashboard**. This is where you can manage your Stripe account, view transactions, and access your API keys.
 + On the left sidebar, go to Developers > API Keys. Here, you'll find your Publishable key and Secret key.
 + Copy the Publishable key and Secret key. The **Publishable key** is used for **client-side** requests, while the **Secret key** is used for **server-side** requests.

 **In the application.properties Add your Secret API key**
```bash
stripe.api.key=YOUR_API_KEY
```

4. **Configure the Database:**
+ Set up a MySQL database.
+ In the **application.properties** update the properties as needed.

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_password

```
5. **Access FoodCart:**

```bash
  Open your browser and go to http://localhost:8080
```

# Deployment:
#### The FoodCart application is fully deployed on AWS, ensuring a robust and scalable environment to handle production-level traffic. Below are the key aspects of the deployment and how you can explore the live application.

## AWS Deployment:
+ **AWS Setup:** The application is hosted on AWS, utilizing services such as EC2 for server instances, RDS for the database, and S3 for static file storage. The deployment process involved setting up a secure and scalable environment, ensuring high availability and reliability.
+ **Swagger Documentation:** To explore the API endpoints and interact with the backend services, visit the Swagger documentation.

# Contribute:
Feel free to contribute to this project by submitting issues, suggesting features, or creating pull requests. Your input is valuable to make this tool even better! 😃

## Feedback
I appreciate your feedback! Please open an issue on GitHub if you encounter any problems or have suggestions for improvement. Your feedback helps enhance the project for everyone.
Please reach out to me at patra.ap.work@gmail.com

## Authors

- [@Avijit Patra](https://github.com/avijit18)

## Swagger Images
![swagger_demo_1](https://github.com/user-attachments/assets/1bbfedcb-20de-4607-aa2d-2b9d6db4d378)

![swagger_demo_2](https://github.com/user-attachments/assets/e182d68a-a91f-4181-b842-d4a1d7d633b5)

![swagger_demo_3](https://github.com/user-attachments/assets/0f9cb931-e2d5-438f-ac28-14a3faaaa85d)

![swagger_demo_4](https://github.com/user-attachments/assets/de90f7bd-2bbd-46e8-97cd-65f24aaeb690)

![swagger_demo_5](https://github.com/user-attachments/assets/3d815d4b-7aee-4066-aa12-dbf4cf61edaa)

## Demo 
https://github.com/user-attachments/assets/37156f5e-869a-4b18-a77c-61d8ceca0c59

https://github.com/user-attachments/assets/d17e56bc-6c76-4e53-bcba-4a430502324a
