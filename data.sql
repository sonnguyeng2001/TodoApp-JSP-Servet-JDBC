USE [TodoApp]
GO
/****** Object:  Table [dbo].[tbl_TODO]    Script Date: 2023-03-23 9:39:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_TODO](
	[id_TODO] [nchar](10) NOT NULL,
	[id_USER] [nchar](10) NULL,
	[title] [nvarchar](100) NULL,
	[note] [nvarchar](100) NULL,
	[status_TODO] [int] NULL,
	[createAt] [datetime] NULL,
	[updateAt] [datetime] NULL,
 CONSTRAINT [PK__tbl_TODO__5B37B918EF099AC2] PRIMARY KEY CLUSTERED 
(
	[id_TODO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_USER]    Script Date: 2023-03-23 9:39:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_USER](
	[id] [nchar](10) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'0152B6AE  ', N'22EFAF8B  ', N'Quét nhà 123', N'', 0, CAST(N'2023-03-20T19:29:26.327' AS DateTime), CAST(N'2023-03-22T19:21:11.137' AS DateTime))
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'12BDEB1D  ', N'30B25D05  ', N'TASK1', N'no', 0, CAST(N'2023-03-17T19:56:38.207' AS DateTime), CAST(N'2023-03-17T19:56:38.207' AS DateTime))
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'2B199D3D  ', N'403AA72C  ', N'rửa chén', N'', 0, CAST(N'2023-03-20T19:34:27.520' AS DateTime), CAST(N'2023-03-20T19:34:27.520' AS DateTime))
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'8A6BC259  ', N'403AA72C  ', N'user4_đi bộ', N'', 0, CAST(N'2023-03-20T19:34:17.983' AS DateTime), CAST(N'2023-03-20T19:34:17.983' AS DateTime))
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'8D8986A6  ', N'22EFAF8B  ', N'Rửa chén', N'', 0, CAST(N'2023-03-22T19:21:06.103' AS DateTime), CAST(N'2023-03-22T19:21:06.103' AS DateTime))
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'A0025899  ', N'403AA72C  ', N'Lau nhà', N'', 0, CAST(N'2023-03-20T19:34:33.237' AS DateTime), CAST(N'2023-03-20T19:34:33.237' AS DateTime))
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'E0D93907  ', N'403AA72C  ', N'Đi chơi', N'', 0, CAST(N'2023-03-20T19:34:48.740' AS DateTime), CAST(N'2023-03-20T19:34:48.740' AS DateTime))
INSERT [dbo].[tbl_TODO] ([id_TODO], [id_USER], [title], [note], [status_TODO], [createAt], [updateAt]) VALUES (N'E9692386  ', N'403AA72C  ', N'Quét nhà', N'', 0, CAST(N'2023-03-20T19:34:38.567' AS DateTime), CAST(N'2023-03-20T19:34:38.567' AS DateTime))
GO
INSERT [dbo].[tbl_USER] ([id], [username], [password]) VALUES (N'22EFAF8B  ', N'username1', N'password1')
INSERT [dbo].[tbl_USER] ([id], [username], [password]) VALUES (N'30B25D05  ', N'username3', N'pass3')
INSERT [dbo].[tbl_USER] ([id], [username], [password]) VALUES (N'403AA72C  ', N'username4', N'password4')
INSERT [dbo].[tbl_USER] ([id], [username], [password]) VALUES (N'7268C201  ', N'username2', N'pass2')
GO
ALTER TABLE [dbo].[tbl_TODO] ADD  CONSTRAINT [df_updateAt]  DEFAULT (NULL) FOR [updateAt]
GO
ALTER TABLE [dbo].[tbl_TODO]  WITH CHECK ADD  CONSTRAINT [fk_todo_user] FOREIGN KEY([id_USER])
REFERENCES [dbo].[tbl_USER] ([id])
GO
ALTER TABLE [dbo].[tbl_TODO] CHECK CONSTRAINT [fk_todo_user]
GO
