use proj1;

if exists (select * from dbo.sysobjects where id = object_id(N'[FK_RulariTesteTabele_Tabele]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [RulariTesteTabele] DROP CONSTRAINT [FK_RulariTesteTabele_Tabele]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TesteTabele_Tabele]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TesteTabele] DROP CONSTRAINT [FK_TesteTabele_Tabele]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_RulariTesteTabele_RulariTeste]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [RulariTesteTabele] DROP CONSTRAINT FK_RulariTesteTabele_RulariTeste

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_RulariTesteViewuri_RulariTeste]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [RulariTesteViewuri] DROP CONSTRAINT FK_RulariTesteViewuri_RulariTeste

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TesteTabele_Teste]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TesteTabele] DROP CONSTRAINT FK_TesteTabele_Teste

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TesteViewuri_Teste]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TesteViewuri] DROP CONSTRAINT FK_TesteViewuri_Teste

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_RulariTesteViewuri_Viewuri]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [RulariTesteViewuri] DROP CONSTRAINT FK_RulariTesteViewuri_Viewuri

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TesteViewuri_Viewuri]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TesteViewuri] DROP CONSTRAINT FK_TesteViewuri_Viewuri

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Tabele]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Tabele]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[RulariTesteTabele]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [RulariTesteTabele]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[RulariTesteViewuri]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [RulariTesteViewuri]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[RulariTeste]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [RulariTeste]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TesteTabele]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TesteTabele]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TesteViewuri]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TesteViewuri]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Teste]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Teste]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Viewuri]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Viewuri]

GO



CREATE TABLE [Tabele] (

	[CodTabel] [int] IDENTITY (1, 1) NOT NULL ,

	[Nume] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [RulariTesteTabele] (

	[CodRulareTest] [int] NOT NULL ,

	[CodTabel] [int] NOT NULL ,

	[IncepeLa] [datetime] NOT NULL ,

	[SeIncheieLa] [datetime] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [RulariTesteViewuri] (

	[CodRulareTest] [int] NOT NULL ,

	[CodView] [int] NOT NULL ,

	[IncepeLa] [datetime] NOT NULL ,

	[SeIncheieLa] [datetime] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [RulariTeste] (

	[CodRulareTest] [int] IDENTITY (1, 1) NOT NULL ,

	[Descriere] [nvarchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,

	[IncepeLa] [datetime] NULL ,

	[SeIncheieLa] [datetime] NULL 

) ON [PRIMARY]

GO



CREATE TABLE [TesteTabele] (

	[CodTest] [int] NOT NULL ,

	[CodTabel] [int] NOT NULL ,

	[NrRanduri] [int] NOT NULL ,

	[Pozitie] [int] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [TesteViewuri] (

	[CodTest] [int] NOT NULL ,

	[CodView] [int] NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [Teste] (

	[CodTest] [int] IDENTITY (1, 1) NOT NULL ,

	[Nume] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 

) ON [PRIMARY]

GO



CREATE TABLE [Viewuri] (

	[CodView] [int] IDENTITY (1, 1) NOT NULL ,

	[Nume] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL 

) ON [PRIMARY]

GO



ALTER TABLE [Tabele] WITH NOCHECK ADD 

	CONSTRAINT [PK_Tabele] PRIMARY KEY  CLUSTERED 

	(

		[CodTabel]

	)  ON [PRIMARY] 

GO



ALTER TABLE [RulariTesteTabele] WITH NOCHECK ADD 

	CONSTRAINT [PK_RulariTesteTabele] PRIMARY KEY  CLUSTERED 

	(

		[CodRulareTest],

		[CodTabel]

	)  ON [PRIMARY] 

GO



ALTER TABLE [RulariTesteViewuri] WITH NOCHECK ADD 

	CONSTRAINT [PK_RulariTesteViewuri] PRIMARY KEY  CLUSTERED 

	(

		[CodRulareTest],

		[CodView]

	)  ON [PRIMARY] 

GO



ALTER TABLE [RulariTeste] WITH NOCHECK ADD 

	CONSTRAINT [PK_RulariTeste] PRIMARY KEY  CLUSTERED 

	(

		[CodRulareTest]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TesteTabele] WITH NOCHECK ADD 

	CONSTRAINT [PK_TesteTabele] PRIMARY KEY  CLUSTERED 

	(

		[CodTest],

		[CodTabel]

	)  ON [PRIMARY] 

GO



ALTER TABLE [TesteViewuri] WITH NOCHECK ADD 

	CONSTRAINT [PK_TesteViewuri] PRIMARY KEY  CLUSTERED 

	(

		[CodTest],

		[CodView]

	)  ON [PRIMARY] 

GO



ALTER TABLE [Teste] WITH NOCHECK ADD 

	CONSTRAINT [PK_Teste] PRIMARY KEY  CLUSTERED 

	(

		[CodTest]

	)  ON [PRIMARY] 

GO



ALTER TABLE [Viewuri] WITH NOCHECK ADD 

	CONSTRAINT [PK_Viewuri] PRIMARY KEY  CLUSTERED 

	(

		[CodView]

	)  ON [PRIMARY] 

GO



ALTER TABLE [RulariTesteTabele] ADD 

	CONSTRAINT [FK_RulariTesteTabele_Tabele] FOREIGN KEY 

	(

		[CodTabel]

	) REFERENCES [Tabele] (

		[CodTabel]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_RulariTesteTabele_RulariTeste] FOREIGN KEY 

	(

		[CodRulareTest]

	) REFERENCES [RulariTeste] (

		[CodRulareTest]

	) ON DELETE CASCADE  ON UPDATE CASCADE 

GO



ALTER TABLE [RulariTesteViewuri] ADD 

	CONSTRAINT [FK_RulariTesteViewuri_RulariTeste] FOREIGN KEY 

	(

		[CodRulareTest]

	) REFERENCES [RulariTeste] (

		[CodRulareTest]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_RulariTesteViewuri_Viewuri] FOREIGN KEY 

	(

		[CodView]

	) REFERENCES [Viewuri] (

		[CodView]

	) ON DELETE CASCADE  ON UPDATE CASCADE 

GO



ALTER TABLE [TesteTabele] ADD 

	CONSTRAINT [FK_TesteTabele_Tabele] FOREIGN KEY 

	(

		[CodTabel]

	) REFERENCES [Tabele] (

		[CodTabel]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_TesteTabele_Teste] FOREIGN KEY 

	(

		[CodTest]

	) REFERENCES [Teste] (

		[CodTest]

	) ON DELETE CASCADE  ON UPDATE CASCADE 

GO



ALTER TABLE [TesteViewuri] ADD 

	CONSTRAINT [FK_TesteViewuri_Teste] FOREIGN KEY 

	(

		[CodTest]

	) REFERENCES [Teste] (

		[CodTest]

	),

	CONSTRAINT [FK_TesteViewuri_Viewuri] FOREIGN KEY 

	(

		[CodView]

	) REFERENCES [Viewuri] (

		[CodView]

	)

GO

