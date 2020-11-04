//
//  AWListContainer.swift
//  talan
//
//  Created by Miguel Panelo on 22/07/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import Foundation
import SwiftUI
import SharedClient

struct ListItem: View {
    var issue: IssueEntryUi
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(issue.link)
                    .font(.system(size: 10))
                    .kerning(1.5)
                    .padding(.bottom)
                Text(issue.title)
                    .font(.system(size: 16))
                    .bold()
                    .padding(.bottom)
                Text(issue.component2())
                    .font(.system(size: 16))
            }
            Spacer()
        }.padding()
    }
}

//struct ListItem_Previews: PreviewProvider {
//    static var previews: some View {
//        ListItem(issue: IssueEntry(
//            title: "Optimize the build speed for your Android project",
//            description: "In this article, Tony Robalik takes a look at the Dependency Analysis Gradle Plugin. This plugin is uniquely designed to solve several build problems in the Android & JVM build ecosystems such as telling you which (if any) of your dependencies are unused and can be removed.",
//            image: "image",
//            link: "www.crazylegend.dev",
//            host: "www.crazylegend.dev",
//            isSponsored: false,
//            type: .article
//        ))
//    }
//}
