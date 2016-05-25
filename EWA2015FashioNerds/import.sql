INSERT INTO `UserRole` (`userRoleId`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO `User` (`Id`, `Language`, `enabled`, `firstName`, `infix`, `lastName`, `password`, `profile`, `requirePasswordChange`, `username`) VALUES
(1, 'nl', b'1', 'Admin', NULL, 'Admin', '$2a$10$NxFy4Kh4i9abzpNEt6JFpej2xzob0WDl9.8ctu/CLVYfDMjmpxm8.', NULL, b'0', 'admin');

INSERT INTO `User_UserRole` (`user_Id`, `userRole_userRoleId`) VALUES
(1, 1);

