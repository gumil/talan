//
//  AWListContainer.swift
//  talan
//
//  Created by Miguel Panelo on 23/07/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import SwiftUI
import SharedClient

struct AWListContainer: View {
    private let viewModel = AWListModule.provideViewModel(AWListModule())()

    @ObservedObject var issueList = IssueList()
    
    func initialize() {
        viewModel.observe { (state) in
            let currentState = state as! IssueListState.Screen
            self.issueList.issues = currentState.issues
        }
        viewModel.dispatch(action: IssueListAction.Refresh())
    }
    
    var body: some View {
        List(issueList.issues, id: \.title) { issue in
            ListItem(issue: issue)
        }
    }
}

class IssueList: ObservableObject {
    @Published var issues = [IssueEntry]()
}
