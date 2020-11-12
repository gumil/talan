//
//  AWListView.swift
//  talan
//
//  Created by Miguel Panelo on 04/11/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import SwiftUI
import SharedClient

struct AWListView: View {
    private let events: AWListEvents

    @ObservedObject
    private var data: ObservableValue<AWListScreen>
    
    init(_ model: AWListModel) {
        self.events = model
        self.data = ObservableValue(model.state)
    }
    
    var body: some View {
        List(self.data.value.issues, id: \.title) { item in
            ListItem(issue: item, onClick: { issue in
                self.events.onItemClicked(issueEntry: issue)
            })
        }
    }
}

//struct ListView_Previews: PreviewProvider {
//    static var previews: some View {
//        ListView(Model())
//    }
//
//    class Model : TodoListModel {
//        let data: Value<TodoListData> =
//            mutableValue(
//                TodoListData(
//                    items: [
//                        TodoItem(id: 1, order: 1, text: "Item 1", isDone: false),
//                        TodoItem(id: 2, order: 2, text: "Item 2", isDone: true),
//                        TodoItem(id: 3, order: 3, text: "Item 3", isDone: false)
//                    ]
//                )
//        )
//
//        func onDoneChanged(id: Int64, isDone: Bool) {
//        }
//
//        func onItemClicked(id: Int64) {
//        }
//    }
//}
//
