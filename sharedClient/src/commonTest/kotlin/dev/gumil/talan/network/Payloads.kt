package dev.gumil.talan.network

internal const val androidWeeklyPayload = """
{
  "issues": [
    {
      "title": "Android Weekly 1",
      "link": "link 1",
      "entries": [
        {
          "title": "title 1-1",
          "description": "desc 1-1",
          "image": "image 1-1",
          "link": "link 1-1",
          "host": "host 1-1",
          "isSponsored": false,
          "type": "UNKNOWN"
        },
        {
          "title": "title 1-2",
          "description": "desc 1-2",
          "image": "image 1-2",
          "link": "link 1-2",
          "host": "host 1-2",
          "isSponsored": false,
          "type": "JOB"
        }
      ],
      "publishDate": "date 1"
    },
    {
      "title": "Android Weekly 2",
      "link": "link 2",
      "entries": [
        {
          "title": "title 2-1",
          "description": "desc 2-1",
          "image": "image 2-1",
          "link": "link 2-1",
          "host": "host 2-1",
          "isSponsored": true,
          "type": "SPONSORED"
        }
      ],
      "publishDate": "date 2"
    }
  ]
}
"""

internal val androidWeeklyIssues = listOf(
    Issue(
        "Android Weekly 1",
        "link 1",
        listOf(
            IssueEntry(
                title = "title 1-1",
                description = "desc 1-1",
                image = "image 1-1",
                link = "link 1-1",
                host = "host 1-1",
                isSponsored = false,
                type = EntryType.UNKNOWN
            ),
            IssueEntry(
                title = "title 1-2",
                description = "desc 1-2",
                image = "image 1-2",
                link = "link 1-2",
                host = "host 1-2",
                isSponsored = false,
                type = EntryType.JOB
            )
        ),
        "date 1"
    ),
    Issue(
        "Android Weekly 2",
        "link 2",
        listOf(
            IssueEntry(
                title = "title 2-1",
                description = "desc 2-1",
                image = "image 2-1",
                link = "link 2-1",
                host = "host 2-1",
                isSponsored = true,
                type = EntryType.SPONSORED
            )
        ),
        "date 2"
    )
)
