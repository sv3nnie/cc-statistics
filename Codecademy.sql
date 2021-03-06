USE [master]
GO
/****** Object:  Database [Codecademy]    Script Date: 1/24/2021 8:12:04 PM ******/
CREATE DATABASE [Codecademy]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Codecademy', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Codecademy.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Codecademy_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Codecademy_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Codecademy] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Codecademy].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Codecademy] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Codecademy] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Codecademy] SET ARITHABORT OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Codecademy] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Codecademy] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Codecademy] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Codecademy] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Codecademy] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Codecademy] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Codecademy] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Codecademy] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Codecademy] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Codecademy] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Codecademy] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Codecademy] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Codecademy] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Codecademy] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Codecademy] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Codecademy] SET RECOVERY FULL 
GO
ALTER DATABASE [Codecademy] SET  MULTI_USER 
GO
ALTER DATABASE [Codecademy] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Codecademy] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Codecademy] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Codecademy] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Codecademy] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Codecademy', N'ON'
GO
ALTER DATABASE [Codecademy] SET QUERY_STORE = OFF
GO
USE [Codecademy]
GO
/****** Object:  Table [dbo].[Certificate]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Certificate](
	[CertificateId] [int] IDENTITY(1,1) NOT NULL,
	[Grade] [float] NOT NULL,
	[Employee] [nvarchar](50) NOT NULL,
	[StudentEmailAddress] [nvarchar](50) NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
	[EnrollmentDate] [date] NOT NULL,
 CONSTRAINT [PK_Certificate_1] PRIMARY KEY CLUSTERED 
(
	[CertificateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Content]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Content](
	[ContentItemId] [int] IDENTITY(1,1) NOT NULL,
	[PublicationDate] [date] NOT NULL,
	[Status] [nvarchar](25) NOT NULL,
	[Type] [nvarchar](20) NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Content] PRIMARY KEY CLUSTERED 
(
	[ContentItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[CourseName] [nvarchar](50) NOT NULL,
	[Module] [int] NULL,
	[Webcast] [int] NULL,
	[Subject] [nvarchar](50) NOT NULL,
	[Difficulty] [nvarchar](20) NOT NULL,
	[IntroductionText] [nvarchar](255) NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Enrollment]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enrollment](
	[EmailAddress] [nvarchar](50) NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
	[EnrollmentDate] [date] NOT NULL,
 CONSTRAINT [PK_Enrollment] PRIMARY KEY CLUSTERED 
(
	[EmailAddress] ASC,
	[CourseName] ASC,
	[EnrollmentDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InterestingCourse]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InterestingCourse](
	[CourseName] [nvarchar](50) NOT NULL,
	[InterestingCourseName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_InterestingCourse] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC,
	[InterestingCourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[Title] [nvarchar](50) NOT NULL,
	[Version] [float] NOT NULL,
	[Description] [nvarchar](255) NULL,
	[ContactName] [nvarchar](50) NULL,
	[ContactEmail] [nvarchar](50) NULL,
	[ContentItemId] [int] NULL,
	[CourseName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Module] PRIMARY KEY CLUSTERED 
(
	[Title] ASC,
	[Version] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Progress]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Progress](
	[StudentEmailAddress] [nvarchar](50) NOT NULL,
	[ContentItemId] [int] NOT NULL,
	[ProgressPercentage] [float] NOT NULL,
 CONSTRAINT [PK_Progress] PRIMARY KEY CLUSTERED 
(
	[StudentEmailAddress] ASC,
	[ContentItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[EmailAddress] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[DateOfBirth] [date] NOT NULL,
	[Gender] [nvarchar](50) NOT NULL,
	[City] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](50) NOT NULL,
	[Country] [nvarchar](50) NOT NULL,
	[PostalCode] [nvarchar](7) NOT NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[EmailAddress] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 1/24/2021 8:12:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[Title] [nvarchar](50) NOT NULL,
	[Duration] [int] NOT NULL,
	[Description] [nvarchar](255) NULL,
	[LecturerName] [nvarchar](50) NULL,
	[LecturerOrg] [nvarchar](50) NULL,
	[ContentItemId] [int] NOT NULL,
	[ViewCount] [int] NULL,
 CONSTRAINT [PK_Webcast] PRIMARY KEY CLUSTERED 
(
	[Title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Certificate] ON 

INSERT [dbo].[Certificate] ([CertificateId], [Grade], [Employee], [StudentEmailAddress], [CourseName], [EnrollmentDate]) VALUES (2, 3, N'Sven', N'bekirwaaijer@gmail.com', N'AngularJS basics explained', CAST(N'2021-01-23' AS Date))
SET IDENTITY_INSERT [dbo].[Certificate] OFF
GO
SET IDENTITY_INSERT [dbo].[Content] ON 

INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (1, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'Java Programming 1')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (2, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'Java Programming 1')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (3, CAST(N'2021-01-23' AS Date), N'Active', N'Webcast', N'Java Programming 1')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (4, CAST(N'2021-01-23' AS Date), N'Active', N'Webcast', N'Java Programming 1')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (5, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'Java Programming 2')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (6, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'Java Programming 2')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (7, CAST(N'2021-01-23' AS Date), N'Active', N'Webcast', N'Java Programming 2')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (9, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'HTML course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (10, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'HTML course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (11, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'HTML advanced course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (12, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'HTML advanced course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (13, CAST(N'2021-01-23' AS Date), N'Active', N'Webcast', N'HTML advanced course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (14, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'JavaScript all-in-one course')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (15, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'JavaScript all-in-one course')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (16, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'JavaScript all-in-one course')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (17, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'JavaScript course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (18, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'JavaScript course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (19, CAST(N'2021-01-23' AS Date), N'Active', N'Webcast', N'JavaScript course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (20, CAST(N'2021-01-23' AS Date), N'Active', N'Webcast', N'JavaScript course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (21, CAST(N'2021-01-23' AS Date), N'Active', N'Webcast', N'JavaScript course for dummies')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (22, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'AngularJS basics explained')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (23, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'AngularJS basics explained')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (24, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'AngularJS basics explained')
INSERT [dbo].[Content] ([ContentItemId], [PublicationDate], [Status], [Type], [CourseName]) VALUES (25, CAST(N'2021-01-23' AS Date), N'Active', N'Module', N'AngularJS basics explained')
SET IDENTITY_INSERT [dbo].[Content] OFF
GO
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'a', NULL, NULL, N'a', N'Easy', N'33')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'AngularJS basics explained', 1, 1, N'JavaScript', N'Medium', N'AngularJS framework explained to everyone')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'HTML advanced course for dummies', 1, 1, N'HTML', N'Medium', N'Advanced HTML course for beginners')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'HTML course for dummies', 1, 1, N'HTML', N'Easy', N'Basics of HTML')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Java Programming 1', 1, 1, N'Java', N'Easy', N'Hello World!')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'Java Programming 2', 1, 1, N'Java', N'Medium', N'Hello World 2!')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'JavaScript all-in-one course', 1, 1, N'JavaScript', N'Hard', N'All-in-one JavaScript course to become an expert!')
INSERT [dbo].[Course] ([CourseName], [Module], [Webcast], [Subject], [Difficulty], [IntroductionText]) VALUES (N'JavaScript course for dummies', 1, 1, N'JavaScript', N'Medium', N'JavaScript course for extreme beginners')
GO
INSERT [dbo].[Enrollment] ([EmailAddress], [CourseName], [EnrollmentDate]) VALUES (N'bekirwaaijer@gmail.com', N'AngularJS basics explained', CAST(N'2021-01-23' AS Date))
INSERT [dbo].[Enrollment] ([EmailAddress], [CourseName], [EnrollmentDate]) VALUES (N'jelani@gmail.com', N'Java Programming 2', CAST(N'2021-01-24' AS Date))
INSERT [dbo].[Enrollment] ([EmailAddress], [CourseName], [EnrollmentDate]) VALUES (N'jouryvede@gmail.com', N'AngularJS basics explained', CAST(N'2021-01-24' AS Date))
GO
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'AngularJS basics explained', N'JavaScript all-in-one course')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'AngularJS basics explained', N'JavaScript course for dummies')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'HTML advanced course for dummies', N'HTML course for dummies')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'HTML course for dummies', N'HTML advanced course for dummies')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Java Programming 1', N'Java Programming 2')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'Java Programming 2', N'Java Programming 1')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'JavaScript all-in-one course', N'AngularJS basics explained')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'JavaScript all-in-one course', N'JavaScript course for dummies')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'JavaScript course for dummies', N'AngularJS basics explained')
INSERT [dbo].[InterestingCourse] ([CourseName], [InterestingCourseName]) VALUES (N'JavaScript course for dummies', N'JavaScript all-in-one course')
GO
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Advanced Javascript 1', 1, N'Advanced JavaScript module 1', N'Rashid', N'rashid@gmail.com', 14, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Advanced JavaScript 2', 1, N'Advanced JavaScript module 2', N'Rashid', N'rashid@gmail.com', 15, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Advanced JavaScript 3', 1, N'Advanced JavaScript module 3', N'Rashid', N'rashid@gmail.com', 16, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'AngularJS module 1', 2, N'AngularJS module 1', N'Damian', N'damian@gmail.com', 22, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'AngularJS module 2', 1, N'AngularJS module 2', N'Damiam', N'damian@gmail.com', 23, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'AngularJS module 3', 1, N'AngularJS module 3', N'Damian', N'damian@gmail.com', 24, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'AngularJS module 4', 1, N'AngularJS module 4', N'Damian', N'damian@gmail.com', 25, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'AngularJS module 5', 1, N'AngularJS module 5', N'Damian', N'damian@gmail.com', NULL, N'a')
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Bootstrap explained 2', 1, N'Bootstrap explanation module 2', N'Pieter Post', N'pieter@gmail.com', 12, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Bootstrap explanation 1', 3, N'Bootstrap explanation module 1', N'Pieter Post', N'pieter@gmail.com', 11, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Classes', 1, N'Learning how to use classes', N'Hans Klaas', N'hans@gmail.com', 5, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Hello World!', 1, N'Writing your first hello world!', N'Hans Klaas', N'hans@gmail.com', 1, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'HTML new lesson', 1, N'HTML', N'Hans Klaas', N'hans@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Index page', 1, N'Writing your first HTML index page', N'Pieter Post', N'pieter@gmail.com', 9, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Interfaces', 1, N'Interfaces in Java', N'Hans Klaas', N'hans@gmail.com', 6, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'JavaScript basics 1', 1, N'JavaScript course for dummies module 1', N'Rashid', N'rashid@gmail.com', 17, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'JavaScript basics 2', 3, N'JavaScript course for dummies module 12', N'Rashid', N'rashid@gmail.com', 18, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'JavaScript new lesson (BASIC)', 1, N'JavaScript basic lesson', N'Rashid', N'rashid@gmail.com', NULL, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Styling', 2, N'Everything about HTML styling...', N'Pieter Post', N'pieter@gmail.com', 10, NULL)
INSERT [dbo].[Module] ([Title], [Version], [Description], [ContactName], [ContactEmail], [ContentItemId], [CourseName]) VALUES (N'Variables...', 1, N'All variables in Java', N'Hans Klaas', N'hans@gmail.com', 2, NULL)
GO
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'bekirwaaijer@gmail.com', 22, 3)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'bekirwaaijer@gmail.com', 23, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'bekirwaaijer@gmail.com', 24, 33.45)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'bekirwaaijer@gmail.com', 25, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jelani@gmail.com', 5, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jelani@gmail.com', 6, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jelani@gmail.com', 7, 44)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jouryvede@gmail.com', 14, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jouryvede@gmail.com', 15, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jouryvede@gmail.com', 16, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jouryvede@gmail.com', 22, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jouryvede@gmail.com', 23, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jouryvede@gmail.com', 24, 0)
INSERT [dbo].[Progress] ([StudentEmailAddress], [ContentItemId], [ProgressPercentage]) VALUES (N'jouryvede@gmail.com', 25, 0)
GO
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'bekirwaaijer@gmail.com', N'Bekir Waaijer', CAST(N'2000-01-20' AS Date), N'Male', N'Almelo', N'Violierstraat 136', N'Netherlands', N'7601 GV')
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'jelani@gmail.com', N'Jelani Verdoes', CAST(N'2000-09-19' AS Date), N'Male', N'Vegelinsoord', N'Nije Kampen 158', N'Netherlands', N'8467 ST')
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'jouryvede@gmail.com', N'Joury van Ede', CAST(N'1999-11-11' AS Date), N'Male', N'Enschede', N'Het Stroink 114', N'Netherlands', N'7542 GX')
INSERT [dbo].[Student] ([EmailAddress], [Name], [DateOfBirth], [Gender], [City], [Address], [Country], [PostalCode]) VALUES (N'sinnemoed@hotmail.com', N'Sinne Moed', CAST(N'2001-01-01' AS Date), N'Female', N'Veenendaal', N'Mahlerpad 163', N'Netherlands', N'3906 ZM')
GO
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'HTML advanced course for dummies', 312, N'HTML advanced course for dummies', N'Karen', N'Avans', 13, 432)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'Java Programming Advanced', 900, N'Java Programming advanced explained with examples', N'John', N'Harvard', 7, 231)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'JavaScript course for dummies P1', 754, N'JavaScript course for dummies P1', N'Rashid', N'Avans', 19, 321)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'JavaScript course for dummies P2', 432, N'JavaScript course for dummies P2', N'Rashid', N'Avans', 20, 755)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'JavaScript course for dummies P3', 342, N'JavaScript course for dummies P3', N'Rashid', N'Avans', 21, 543)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'The basics of Java Programming', 600, N'Basics of Java Programming explained in 10 minutes', N'John', N'Harvard', 3, 956)
INSERT [dbo].[Webcast] ([Title], [Duration], [Description], [LecturerName], [LecturerOrg], [ContentItemId], [ViewCount]) VALUES (N'The basics of Java Programming (2)', 300, N'Basics of Java Programming', N'John', N'Harvard', 4, 432)
GO
ALTER TABLE [dbo].[Webcast] ADD  CONSTRAINT [DF_Webcast_ViewCount]  DEFAULT ((0)) FOR [ViewCount]
GO
ALTER TABLE [dbo].[Certificate]  WITH CHECK ADD  CONSTRAINT [FK_Certificate_Enrollment] FOREIGN KEY([StudentEmailAddress], [CourseName], [EnrollmentDate])
REFERENCES [dbo].[Enrollment] ([EmailAddress], [CourseName], [EnrollmentDate])
GO
ALTER TABLE [dbo].[Certificate] CHECK CONSTRAINT [FK_Certificate_Enrollment]
GO
ALTER TABLE [dbo].[Content]  WITH CHECK ADD  CONSTRAINT [FK_Content_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Content] CHECK CONSTRAINT [FK_Content_Course]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Course]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Student] FOREIGN KEY([EmailAddress])
REFERENCES [dbo].[Student] ([EmailAddress])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Student]
GO
ALTER TABLE [dbo].[InterestingCourse]  WITH CHECK ADD  CONSTRAINT [FK_InterestingCourse_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[InterestingCourse] CHECK CONSTRAINT [FK_InterestingCourse_Course]
GO
ALTER TABLE [dbo].[InterestingCourse]  WITH CHECK ADD  CONSTRAINT [FK_InterestingCourse_Course1] FOREIGN KEY([InterestingCourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[InterestingCourse] CHECK CONSTRAINT [FK_InterestingCourse_Course1]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Content] FOREIGN KEY([ContentItemId])
REFERENCES [dbo].[Content] ([ContentItemId])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Content]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Course]
GO
ALTER TABLE [dbo].[Progress]  WITH CHECK ADD  CONSTRAINT [FK_Progress_Content] FOREIGN KEY([ContentItemId])
REFERENCES [dbo].[Content] ([ContentItemId])
GO
ALTER TABLE [dbo].[Progress] CHECK CONSTRAINT [FK_Progress_Content]
GO
ALTER TABLE [dbo].[Progress]  WITH CHECK ADD  CONSTRAINT [FK_Progress_Student] FOREIGN KEY([StudentEmailAddress])
REFERENCES [dbo].[Student] ([EmailAddress])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Progress] CHECK CONSTRAINT [FK_Progress_Student]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [FK_Webcast_Content] FOREIGN KEY([ContentItemId])
REFERENCES [dbo].[Content] ([ContentItemId])
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [FK_Webcast_Content]
GO
USE [master]
GO
ALTER DATABASE [Codecademy] SET  READ_WRITE 
GO
